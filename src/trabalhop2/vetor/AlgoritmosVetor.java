package trabalhop2.vetor;

/**
 * @author Henrique K.
 */
public class AlgoritmosVetor {
    private int[] vet;
    private int tl;

    public AlgoritmosVetor(int[] vet) {
        this.vet = vet;
        this.tl = vet.length;
    }
    
    public void swap(int[] vet, int i, int j)
    {
        int temp = vet[i];
        vet[i] = vet[j];
        vet[j] = temp;
    }
    
    public void exibe()
    {
        System.out.print("Vetor: ");
        for (int i = 0; i < vet.length; i++) {
            System.out.print(vet[i] + " ");
        }
        System.out.println("\n");
    }
    
    public void insercao_direta()
    {
        int aux, pos;
        for (int i = 1; i < tl; i++) {
            for (pos = i, aux = vet[i]; pos > 0 && aux < vet[pos - 1]; pos--)
                vet[pos] = vet[pos - 1];
            vet[pos] = aux;
        }
    }
    
    public void insercao_binaria()
    {
        int aux, pos;
        for (int i = 1; i < tl; i++) {
            aux = vet[i];
            pos = busca_binaria(aux, i);
            for (int j = i; j > pos; j--)
                vet[j] = vet[j - 1];
            
            vet[pos] = aux;
        }
    }
    private int busca_binaria(int elem, int tl)
    {
        int ini = 0, fim = tl - 1, meio = tl/2;
        while(ini < fim && elem != vet[meio])
        {
            if(elem < vet[meio])
                fim = meio - 1;
            else
                ini = meio + 1;
            
            meio = (ini + fim) / 2;
        }
        if(elem < vet[meio])
            return meio;
        return meio + 1;
    }
    
    public void selecao_direta()
    {
        int menor, posMenor;
        for (int i = 0; i < tl - 1; i++) {
            menor = vet[i];
            posMenor = i;
            for (int j = i + 1; j < tl; j++) {
                if (menor > vet[j]) {
                    menor = vet[j];
                    posMenor = j;
                }
            }
            vet[posMenor] = vet[i];
            vet[i] = menor;
        }
    }
    
    public void metodo_bolha()
    {
        for (int tl1 = tl; tl1 > 1; tl1--) 
            for (int i = 0; i < tl1 - 1; i++)
                if(vet[i] > vet[i + 1])
                    swap(vet, i, i + 1);
    }
    
    public void shake_sort()
    {
        for (int inicio = 0, fim = tl - 1; inicio < fim; inicio++) {
            for (int i = inicio; i < fim; i++)
                if (vet[i] > vet[i + 1])
                    swap(vet, i, i + 1);
            fim--;
            for (int j = fim; j > inicio; j--)
                if (vet[j] < vet[j - 1])
                    swap(vet, j, j - 1);
        }
    }
    
    public void heap_sort()
    {
        int fd, fe, pai, maiorf, tl2, aux;
        tl2 = tl;
        while(tl2 > 1)
        {
            pai = tl2/2 - 1;
            while(pai >= 0)
            {
                fe = 2* pai + 1;
                fd = fe + 1;
                if(fd < tl2 && vet[fd] > vet[fe])
                    maiorf = fd;
                else
                    maiorf = fe;
                
                if(vet[maiorf] > vet[pai])
                    swap(vet, maiorf, pai);
                pai--;
            }
            swap(vet, 0, tl2 - 1);
            tl2--;
        }
    }
    
    public void shell_sort()
    {
        int i, j, k, dist, aux;
        for (dist = 4; dist > 0; dist /= 2) {
            for (i = 0; i < dist; i++) {
                for (j = i; j + dist < tl; j += dist) {
                    if(vet[j] > vet[j + dist])
                    {
                        swap(vet, j, j + dist);
                        for (k = j; k - dist >= i && vet[k] < vet[k - dist]; k -= dist) {
                            swap(vet, k, k - dist);
                        }
                    }
                }
            }
        }
    }
    
    public void quick_sort_sp()
    {
        quick_sp(0, tl - 1);
    }
    
    private void quick_sp(int ini, int fim)
    {
        int i = ini, j = fim, aux;
        boolean flag = true;
        while(i < j)
        {
            if(flag)
                while(i < j && vet[i] <= vet[j])
                    i++;
            else
                while(i < j && vet[j] >= vet[i])
                    j--;
            aux = vet[j];
            vet[j] = vet[i];
            vet[i] = aux;
            flag = !flag;
        }
        if(ini <i - 1)
            quick_sp(ini, i - 1);
        if(j + 1 < fim)
            quick_sp(j + 1, fim);
    }
    
    public void quick_iterativo()
    {
        int l = 0;
        int h = tl - 1;
        int[] stack = new int[h - l + 1];
        int top = -1;
        stack[++top] = l;
        stack[++top] = h;
        
        while(top >= 0)
        {
            h = stack[top--];
            l = stack[top--];
            
            int p = particao(l, h);
            
            if(p - 1 > l)
            {
                stack[++top] = l;
                stack[++top] = p - 1;
            }
            
            if(p + 1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }
    
    private int particao(int low, int high)
    {
        int i = low - 1;
        int pivo = vet[high];
        for (int j = low; j < high; j++) {
            if(vet[j] <= pivo)
            {
                i++;
                swap(vet, i, j);
            }
        }
        swap(vet, high, i + 1);
        
        return i + 1;
    }
    
    public void merge()
    {
        int[] aux = new int[tl];
        merge_rec(aux, 0, tl - 1);
    }
    
    private void fusao(int[] aux, int ini1, int fim1, int ini2, int fim2)
    {
        int k = 0, i = ini1, j = ini2;
        
        while(i <= fim1 && j <= fim2)
            aux[k++] = vet[i] < vet[j] ? vet[i++] : vet[j++];
        
        while(i <= fim1)
            aux[k++] = vet[i++];
        
        while(j <= fim1)
            aux[k++] = vet[j++];
        
        for (int l = 0; l < k; l++)
            vet[l + ini1] = aux[l];
    }
    
    private void merge_rec(int[] aux, int esq, int dir)
    {
        int meio;
        if(esq < dir)
        {
            meio = (esq + dir)/2;
            merge_rec(aux, esq, meio);
            merge_rec(aux, meio + 1, dir);
            fusao(aux, esq, meio, meio + 1, dir);
        }
    }
}
