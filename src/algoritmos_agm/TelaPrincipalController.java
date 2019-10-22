/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_agm;

import Algoritmos.Ligacao;
import Algoritmos.Prim;
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

    private Prim algoritmo;
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

            linha = buffRead.readLine();
            algoritmo = new Prim(linha);

            int i = 0;
            while ((linha = buffRead.readLine()) != null)
            {
                algoritmo.insereNumeros(linha, i);
                i++;
            }

            buffRead.close();
            txEntrada.setText(algoritmo.toString());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void evtAbreArquivo(MouseEvent event)
    {
        if (event == null || event.getButton() == MouseButton.MIDDLE)
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
        try
        {
            Integer c = algoritmo.prim();
            ArrayList<String> ars = algoritmo.getArestas();
            int val;
            String[] t;
            tbAGM.getItems().clear();
            for (int i = 0; i < algoritmo.getArestas().size(); i++)
            {
                val = algoritmo.getValor().get(i);
                t = algoritmo.getArestas().get(i).split(",");
                tbAGM.getItems().add(new Ligacao(t[0].charAt(0), t[1].charAt(0), val));
            }
            lblCusto.setText(c.toString());
        } catch (Exception ex)
        {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()+"\n"+ex.getCause(), ButtonType.OK).show();
        }

    }

    @FXML
    private void evtAbreArquivo2(MouseEvent event)
    {
        evtAbreArquivo(null);
    }
}
