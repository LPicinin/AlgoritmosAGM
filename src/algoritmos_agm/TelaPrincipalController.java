/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_agm;

import Algoritmos.Prim;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author luish
 */
public class TelaPrincipalController implements Initializable
{

    private Label label;
    @FXML
    private TextArea txEntrada;
    @FXML
    private TextArea txSaida;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    @FXML
    private void evtAbreArquivo(MouseEvent event)
    {
        if (event.getButton() == MouseButton.MIDDLE)
        {

            FileChooser fc = new FileChooser();
            fc.setTitle("Selecione o ArquivoTexto!!!");
            fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Apenas", ".txt"));
            File arq = fc.showOpenDialog(null);
            if (arq != null)
            {
                try
                {
                    RandomAccessFile rf = new RandomAccessFile(arq, "r");
                    String line;
                    String ma = "";
                    while ((line = rf.readLine()) != null)
                    {
                        ma += line + "\n";
                    }
                    rf.close();
                    txEntrada.setText(ma);
                } catch (Exception ex)
                {
                    new Alert(Alert.AlertType.ERROR, "Impossivel Abrir o Arquivo:\n" + ex.getCause(), ButtonType.OK).show();
                }
            }
        }
    }

    @FXML
    private void evtGeraAgm(MouseEvent event)
    {
        Integer[][] m = converte_MA(txEntrada.getText());
        Prim p = new Prim(m);
        p.processaMA();
        new Alert(Alert.AlertType.INFORMATION, p.getCusto().toString(), ButtonType.OK).show();
    }

    private Integer[][] converte_MA(String ma)
    {
        String[] linhas = ma.split("\n");
        String[] colunas;
        int dimensao = linhas.length-1;
        Integer tmp;
        Integer[][] m = new Integer[dimensao][dimensao];

        for (int i = 1; i < linhas.length; i++)
        {
            colunas = linhas[i].split(" ");
            for (int j = 0; j < colunas.length; j++)
            {
                tmp = Integer.parseInt(colunas[j]);
                tmp = (tmp == 0) ? null : tmp;
                m[i - 1][j] = tmp;
            }
        }
        return m;
    }

}
