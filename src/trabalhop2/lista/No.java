package trabalhop2.lista;
/**
 *
 * @author Henrique K.
 */
public class No {
    private int info;
    private No ant;
    private No prox;
    
    public No(int info, No ant, No prox)
    {
        this.ant = ant;
        this.prox = prox;
        this.info = info;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}
