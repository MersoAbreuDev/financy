package com.financy.financy.enums;

public enum Role {


    USER("USER"), ADMIN("ADMIN"),
    ALUNO("ALUNO"), PROFESSOR("PROFESSOR"),

    RESPONSAVEL("RESPONSAVEL");

    private String descricao;

    public static Role buscarRole(String rl) {
        Role rle = null;
        for (Role role : Role.values()) {
            if (role.getDescricao().equals(rl.toUpperCase())) {
                rle = role;
                break;
            }
        }
        return rle;
    }

    private Role(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
