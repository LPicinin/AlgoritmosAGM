/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_agm;

import Algoritmos.Ligacao;
import Algoritmos.MA;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author luish
 */
public class TelaPrincipalController implements Initializable
{

    private MA ma;
    @FXML
    private TextArea txEntrada;
    @FXML
    private TableView<Ligacao> tbAGM;
    @FXML
    private TableColumn<Ligacao, Integer> colCusto;
    @FXML
    private TableColumn<Ligacao, Character> colLigOrigem;
    @FXML
    private TableColumn<Ligacao, Character> colLigDestino;
    @FXML
    private Label lblCusto;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        colLigOrigem.setCellValueFactory(new PropertyValueFactory<>("o"));
        colLigDestino.setCellValueFactory(new PropertyValueFactory<>("d"));
        colCusto.setCellValueFactory(new PropertyValueFactory<>("custo"));
    }

    public void leitorMA(String path)
    {
        try
        {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = "";

            ma = new MA();

            linha = buffRead.readLine();

            ma.getParaTL(linha);
            ma.inicializaLetra();
            ma.inicializaNumero();
            ma.insereLetras(linha);

            int i = 0;
            while (true)
            {
                linha = buffRead.readLine();

                if (linha != null)
                {
                    ma.insereNumeros(linha, i);
                    i++;
                } else
                {
                    break;
                }
            }

            buffRead.close();
            txEntrada.setText(ma.exibe());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
                leitorMA(arq.getAbsolutePath());
            }
        }
    }

    @FXML
    private void evtGeraAgm(MouseEvent event)
    {
        ma.prim();
        ArrayList<String> t = ma.getT();
        int val;
        String []text1;
        tbAGM.getItems().clear();
        for (int i = 0; i < ma.getT().size(); i++)
        {
            val = Integer.parseInt(ma.getValor().get(i));
            text1 = ma.getT().get(i).split(",");
            tbAGM.getItems().add(new Ligacao(text1[0].charAt(0), text1[1].charAt(0), val));
        }
        lblCusto.setText(ma.getCusto().toString());
    }
}
