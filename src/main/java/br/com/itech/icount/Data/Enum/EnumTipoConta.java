package br.com.itech.icount.Data.Enum;

public enum EnumTipoConta {

    PAGAR("P", "Pagar"),
    RECEBER("R", "Receber");

    private final String chave;

    private final String descricao;

    EnumTipoConta(String chave, String descricao) {
        this.chave = chave;
        this.descricao = descricao;
    }

    public String getChave() { return this.chave; }

    public String getDescricao() { return this.descricao; }

}
