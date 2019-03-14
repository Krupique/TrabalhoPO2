package trabalhop2.lista;
/**
 * @author Henrique K.
 */
public class Lista {
    private No inicio;
    private No fim;
    private int tl;
    
    public Lista(){
        inicio = fim = null;
    }
    
    public void inicializa(){
       inicio = fim = null;
    }
    
    public void inserir_inicio(int info){
        No node = new No(info, null, inicio);
        if(inicio == null)
            fim = node;
        else
            inicio.setAnt(node);
        inicio = node;
    }
    
    public void inserir_final(int info){
        No node = new No(info, fim, null);
        if(fim == null)
            inicio = node;
        else
            fim.setProx(node);
        fim = node;
    }
    
    public void exibir(){
        No aux = inicio;
        System.out.print("Lista: ");
        while(aux != null)
        {
            System.out.print(aux.getInfo() + " ");
            aux = aux.getProx();
        }
        System.out.println("");
    }
    
    public void criar_lista(int[] vet)
    {
        for (int i = 0; i < vet.length; i++)
            inserir_final(vet[i]);
    }
    
    public int getTL()
    {
        tl = 0;
        for (No i = inicio; i != fim; i = i.getProx())
            tl++;
        return tl;
    }
    
    ///Funções em Lista encadeada
    public void insercao_direta(){
        No i, pos;
        int aux;
        for (i = inicio.getProx(); i != null; i = i.getProx()) 
        {
            for (pos = i, aux = i.getInfo(); pos != inicio && aux < pos.getAnt().getInfo(); pos = pos.getAnt()) {
                pos.setInfo(pos.getAnt().getInfo());
            }
            pos.setInfo(aux);
        }
    }
    
    public void selecao_direta(){
        int menor;
        No posMenor;
        for (No i = inicio; i != fim; i = i.getProx()) {
            menor = i.getInfo();
            posMenor = i;
            for (No j = i.getProx(); j != null; j = j.getProx())
                if (menor > j.getInfo()) {
                    menor = j.getInfo();
                    posMenor = j;
                }
            posMenor.setInfo(i.getInfo());
            i.setInfo(menor);
        }
    }
    
    public void metodo_bolha(){
        int aux;
        for (No tl1 = fim; tl1 != inicio.getProx(); tl1 = tl1.getAnt()) {
            for (No i = inicio; i != tl1; i = i.getProx()) {
                if(i.getInfo() > i.getProx().getInfo())
                {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                }
            }
        }
    }
    
    public void shake_sort(){
        int aux;
        for (No inicio = this.inicio, fim = this.fim; inicio != fim.getAnt(); inicio = inicio.getProx()) {
            for (No i = inicio; i != fim; i = i.getProx()) 
                if (i.getInfo() > i.getProx().getInfo()) {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                }
            fim = fim.getAnt();
            for (No j = fim; j != inicio; j = j.getAnt()) 
                if (j.getInfo() < j.getAnt().getInfo()) {
                    aux = j.getInfo();
                    j.setInfo(j.getAnt().getInfo());
                    j.getAnt().setInfo(aux);
                }
        }
    }
}
