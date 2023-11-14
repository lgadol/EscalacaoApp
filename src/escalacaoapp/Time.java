package escalacaoapp;

public class Time {
    private Formacao formacao;
    private String tecnico;

    public Time(Formacao formacao, String tecnico) {
        this.formacao = formacao;
        this.tecnico = tecnico;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }
}
