package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Luish
 */
public class Prim
{

    private String[] vertices;
    private int ma[][];
    private int tl;
    private ArrayList<String> lig;
    private Integer custoG;
    private ArrayList<Integer> custos;

    public Prim()
    {

    }

    public Prim(String linha)
    {
        getParaTL(linha);
        inicializaLetra();
        inicializaNumero();
        insereLetras(linha);
    }

    public ArrayList<Integer> getValor()
    {
        return custos;
    }

    public void setValor(ArrayList<Integer> valor)
    {
        this.custos = valor;
    }

    public String[] getLetra()
    {
        return vertices;
    }

    public void setLetra(String[] letra)
    {
        this.vertices = letra;
    }

    public int[][] getMA()
    {
        return ma;
    }

    public void setMA(int[][] numero)
    {
        this.ma = numero;
    }

    public int getTl()
    {
        return tl;
    }

    public void setTl(int tl)
    {
        this.tl = tl;
    }

    public ArrayList<String> getArestas()
    {
        return lig;
    }

    public void setT(ArrayList<String> T)
    {
        this.lig = T;
    }

    public void inicializaLetra()
    {
        vertices = new String[tl];
    }

    public void inicializaNumero()
    {
        ma = new int[tl][tl];
    }

    public Integer getCusto()
    {
        return custoG;
    }

    public void setCusto(int Custo)
    {
        this.custoG = Custo;
    }

    public void getParaTL(String letras)
    {
        int aux = 0;
        String[] param = letras.split(" ");
        aux = param.length;
        setTl(aux);
    }

    public void insereLetras(String letras)
    {
        int j = 0;
        String[] param = letras.split(" ");
        for (String l : param)
        {
            vertices[j++] = l;
        }
    }

    public void insereNumeros(String numeros, int pos)
    {
        String aux, aux2 = "";
        int j = 0;

        for (int i = 0; i < numeros.length(); i++)
        {
            aux = numeros.charAt(i) + "";
            if (!aux.contains(" "))
            {
                aux2 += aux;
            } else
            {
                ma[pos][j++] = Integer.parseInt(aux2);
                aux2 = "";
            }
        }
    }

    @Override
    public String toString()
    {
        String texto = "       ";

        for (int i = 0; i < tl; i++)
        {
            texto += vertices[i];
            texto += "    ";
        }

        texto += "\n";

        for (int i = 0; i < tl; i++)
        {
            texto += vertices[i];
            texto += "    ";

            for (int j = 0; j < tl; j++)
            {
                texto += ma[i][j];
                texto += "    ";
            }
            texto += "\n";
        }

        return texto;
    }

    public int prim()
    {
        int[] posIndex = new int[2];
        custoG = 0;
        int[] flag = new int[tl];
        flag[0] = 1;
        //flag[0] = getPosInicial();
        lig = new ArrayList<>();
        custos = new ArrayList<>();
        int menor = Integer.MAX_VALUE;

        for (int passos = 0; passos < vertices.length - 1; passos++)
        {
            for (int i = 0; i < vertices.length; i++)
            {
                for (int j = 0; j < vertices.length; j++)
                {
                    //se esta linha está habilitada e ainda não desabilitei esa coluna
                    if (flag[i] == 1 && flag[j] == 0)
                    {
                        if (ma[i][j] != 0 && menor > ma[i][j])
                        {
                            menor = ma[i][j];
                            posIndex[0] = i;
                            posIndex[1] = j;
                        }
                    }
                }
            }

            int pos1 = posIndex[0];
            int pos2 = posIndex[1];

            flag[pos1] = 1;
            flag[pos2] = 1;

            if (menor < Integer.MAX_VALUE)
            {
                custoG += menor;
                custos.add(menor);
                lig.add(vertices[pos1] + "," + vertices[pos2]);
            }
            menor = Integer.MAX_VALUE;
        }
        return custoG;
    }

    private int getPosInicial()
    {
        int menor = Integer.MAX_VALUE;
        int pos = 1;//default
        for (int i = 0; i < vertices.length; i++)
        {
            for (int j = 0; j < vertices.length; j++)
            {
                if(ma[i][j] < menor)
                {
                    menor = ma[i][j];
                    pos = j;
                }
            }
        }
        return pos;
    }

}
