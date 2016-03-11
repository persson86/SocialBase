package com.project2016.lfspersson.socialbasetest1;

import android.database.Cursor;

public class InscritosDB {
    public static final String TABLENAME = "inscritos";

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";

    private long id;
    private String nome;
    private String email;

    public InscritosDB( ) {

    }

    public InscritosDB( Cursor c ) {
        id = c.getInt( c.getColumnIndex( ID ) );
        nome = c.getString(c.getColumnIndex(NOME));
        email = c.getString( c.getColumnIndex( EMAIL ) );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Inscrito{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
