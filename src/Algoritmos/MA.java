package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author hiroshi
 */
public class MA
{

    private String letra[];
    private int ma[][];
    private int tl;
    private ArrayList<String> T;
    private Integer custo;
    private ArrayList<String> valor;

    public MA()
    {
        this.letra = null;
        this.ma = null;
        this.tl = 0;
        this.T = null;
        this.custo = 0;
        //this.historico = null;
    }

    public ArrayList<String> getValor()
    {
        return valor;
    }

    public void setValor(ArrayList<String> valor)
    {
        this.valor = valor;
    }

    public String[] getLetra()
    {
        return letra;
    }

    public void setLetra(String[] letra)
    {
        this.letra = letra;
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

    public ArrayList<String> getT()
    {
        return T;
    }

    public void setT(ArrayList<String> T)
    {
        this.T = T;
    }

    public void inicializaLetra()
    {
        letra = new String[tl];
    }

    public void inicializaNumero()
    {
        ma = new int[tl][tl];
    }

    public Integer getCusto()
    {
        return custo;
    }

    public void setCusto(int Custo)
    {
        this.custo = Custo;
    }

    public void getParaTL(String letras)
    {
        int aux = 0;
        String[] param = letras.split(" ");
        for (String string : param)
        {
            aux++;
        }

        setTl(aux);
    }

    public void insereLetras(String letras)
    {
        String aux = "";
        int j = 0;

        for (int i = 0; i < letras.length(); i++)
        {
            if (letras.charAt(i) != ' ')
            {
                aux += letras.charAt(i) + "";
            } else
            {
                letra[j++] = aux;
                aux = "";
            }
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

    public String exibe()
    {
        String texto = "       ";

        for (int i = 0; i < tl; i++)
        {
            texto += letra[i];
            texto += "    ";
        }

        texto += "\n";

        for (int i = 0; i < tl; i++)
        {
            texto += letra[i];
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
        int menor = Integer.MAX_VALUE;
        int []posIndex = new int[2];
        T = new ArrayList<>();
        custo = 0;
        int[] flag = new int[tl];
        flag[0] = 1;
        valor = new ArrayList<>();

        for (int aux = 0; aux < tl - 1; aux++)
        {
            for (int i = 0; i < tl; i++)
            {
                for (int j = 0; j < tl; j++)
                {
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

            if (menor != Integer.MAX_VALUE)
            {
                valor.add("" + menor);
                T.add(letra[pos1]+","+ letra[pos2]);
                custo += menor;
            }

            menor = Integer.MAX_VALUE;
        }
        return custo;
    }

}
