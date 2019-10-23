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
    private int qt_vertices;
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

    public int getQt_vertices()
    {
        return qt_vertices;
    }

    public void setQt_vertices(int qt_vertices)
    {
        this.qt_vertices = qt_vertices;
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
        vertices = new String[qt_vertices];
    }

    public void inicializaNumero()
    {
        ma = new int[qt_vertices][qt_vertices];
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
        setQt_vertices(aux);
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
        String[] temp = numeros.split(" ");
        for (int i = 0; i < temp.length; i++)
        {
            ma[pos][i] = Integer.parseInt(temp[i]);
        }
    }

    @Override
    public String toString()
    {
        String texto = "       ";

        for (int i = 0; i < qt_vertices; i++)
        {
            texto += vertices[i];
            texto += "    ";
        }

        texto += "\n";

        for (int i = 0; i < qt_vertices; i++)
        {
            texto += vertices[i];
            texto += "    ";

            for (int j = 0; j < qt_vertices; j++)
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
        int[] flag = new int[vertices.length];
        flag[0] = 1;
        //flag[0] = getPosInicial();
        lig = new ArrayList<>();
        custos = new ArrayList<>();
        int menor = Integer.MAX_VALUE;

        for (int passos = 0; passos < vertices.length - 1; passos++)
        {
            for (int lin = 0; lin < vertices.length; lin++)
            {
                for (int col = 0; col < vertices.length; col++)
                {
                    if (flag[lin] == 1 && flag[col] == 0)
                    {
                        if (ma[lin][col] != 0 && ma[lin][col] < menor)
                        {
                            menor = ma[lin][col];
                            posIndex[0] = lin;
                            posIndex[1] = col;
                        }
                    }
                }
            }

            int l = posIndex[0];
            int c = posIndex[1];

            flag[l] = 1;
            flag[c] = 1;

            if (menor < Integer.MAX_VALUE)
            {
                custoG += menor;
                custos.add(menor);
                lig.add(vertices[l] + "," + vertices[c]);
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
                if (ma[i][j] < menor)
                {
                    menor = ma[i][j];
                    pos = j;
                }
            }
        }
        return pos;
    }

}
