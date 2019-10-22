/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

/**
 *
 * @author Aluno
 */
public class Ligacao
{
    private char o;
    private char d;
    private int custo;

    public Ligacao(char o, char d, int custo)
    {
        this.o = o;
        this.d = d;
        this.custo = custo;
    }

    public char getO()
    {
        return o;
    }

    public void setO(char o)
    {
        this.o = o;
    }

    public char getD()
    {
        return d;
    }

    public void setD(char d)
    {
        this.d = d;
    }

    

    public int getCusto()
    {
        return custo;
    }

    public void setCusto(int custo)
    {
        this.custo = custo;
    }
    
}
