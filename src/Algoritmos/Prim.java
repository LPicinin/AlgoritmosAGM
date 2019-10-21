/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luish
 */
public class Prim
{

    private Integer[][] ma;
    private List<Boolean> list_flag_vertical;
    private List<Boolean> list_flag_horizontal;
    private List<int[]> agm;
    private Integer custo;

    public Prim(Integer[][] ma)
    {
        this.ma = ma;
        this.custo = null;
        list_flag_vertical = Arrays.asList(new Boolean[ma[0].length]);
        list_flag_horizontal = Arrays.asList(new Boolean[ma[0].length]);

        Collections.fill(list_flag_horizontal, true);
        Collections.fill(list_flag_vertical, false);
    }

    public void processaMA()
    {
        int pos = getPosInit();//posição inicial, apartir desse nó a AGM será gerada
        int[] index;//[0] coluna e [1] linha 
        Integer[][] m = ma.clone();
        Collections.fill(list_flag_horizontal, true);
        Collections.fill(list_flag_vertical, false);

        agm = new ArrayList<>();
        while (list_flag_horizontal.contains(true))
        {
            list_flag_vertical.set(pos, Boolean.TRUE);
            index = getProximoNoComMenorPeso(m);

            System.out.println((char) (index[0] + 'a') + "," + (char) (index[1] + 'a'));
            agm.add(index.clone());
            list_flag_horizontal.set(index[0], Boolean.FALSE);
            pos = index[1];
        }
    }

    /**
     *
     * @return custo da arvore gerada
     */
    public Integer getCusto()
    {
        if (custo == null)
        {
            custo = 0;
            for (int[] is : this.agm)
            {
                //custo += ma[is[0]][is[1]];
            }
        }
        return custo;
    }

    private int getPosInit()//mudar regra caso seja necessário
    {
        return 0;
    }

    private int[] getProximoNoComMenorPeso(Integer[][] ma)
    {
        int[] index = new int[2];
        int menor = Integer.MAX_VALUE;
        for (int i = 0; i < ma.length; i++)
        {
            for (int j = 0; j < ma[0].length; j++)
            {
                if (list_flag_horizontal.get(j) && ma[i][j] != null && ma[i][j] < menor)
                {
                    index[0] = i;
                    index[1] = j;
                    menor = ma[i][j];
                }
            }
        }
        return index;
    }

}
