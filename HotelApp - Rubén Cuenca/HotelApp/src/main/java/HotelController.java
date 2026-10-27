import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class HotelController {

    @FXML private Label lblHotel;
    @FXML private TableView<Element> tablaHabitaciones;
    @FXML private TableColumn<Element, String> colNumero;
    @FXML private TableColumn<Element, String> colTipo;
    @FXML private TableColumn<Element, String> colCapacidad;
    @FXML private TableColumn<Element, String> colPrecio;
    @FXML private TableColumn<Element, String> colEstado;
    @FXML private TextField txtNumero;
    @FXML private Label lblResultado;

    private NodeList habitaciones;

    @FXML
    private void initialize() {
        // Relacionamos las columnas con los datos de cada habitación.
        colNumero.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getAttribute("numero")));
        colTipo.setCellValueFactory(dato -> new SimpleStringProperty(texto(dato.getValue(), "tipo")));
        colCapacidad.setCellValueFactory(dato -> new SimpleStringProperty(texto(dato.getValue(), "capacidad")));
        colPrecio.setCellValueFactory(dato -> new SimpleStringProperty(texto(dato.getValue(), "precioNoche")));
        colEstado.setCellValueFactory(dato -> new SimpleStringProperty(estado(dato.getValue())));

        // Leemos los datos del XML al abrir la aplicación.
        try (InputStream archivo = getClass().getResourceAsStream("/reservas.xml")) {
            if (archivo == null) {
                lblResultado.setText("No encontramos el archivo reservas.xml.");
                return;
            }

            Document documento = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(archivo);

            Element hotel = documento.getDocumentElement();
            lblHotel.setText(hotel.getElementsByTagName("nombre").item(0).getTextContent()
                    + " - " + hotel.getElementsByTagName("ciudad").item(0).getTextContent());

            habitaciones = documento.getElementsByTagName("habitacion");

            // Recorremos las habitaciones y mostramos sus datos.
            for (int i = 0; i < habitaciones.getLength(); i++) {
                Element habitacion = (Element) habitaciones.item(i);
                tablaHabitaciones.getItems().add(habitacion);
            }
        } catch (Exception e) {
            lblResultado.setText("No hemos podido leer reservas.xml. Revisa el archivo.");
        }
    }

    @FXML
    private void buscarHabitacion() {
        String numero = txtNumero.getText().trim();

        // Comprobamos que se haya escrito un número válido.
        if (numero.isEmpty()) {
            lblResultado.setText("Escribe el número de la habitación.");
            return;
        }

        try {
            if (Integer.parseInt(numero) <= 0) {
                lblResultado.setText("Escribe un número mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            lblResultado.setText("Introduce solo un número de habitación.");
            return;
        }

        if (habitaciones == null) {
            lblResultado.setText("No hay datos cargados. Revisa reservas.xml.");
            return;
        }

        // Buscamos el número entre las habitaciones leídas del XML.
        for (int i = 0; i < habitaciones.getLength(); i++) {
            Element habitacion = (Element) habitaciones.item(i);

            if (habitacion.getAttribute("numero").equals(numero)) {
                tablaHabitaciones.getSelectionModel().select(habitacion);
                tablaHabitaciones.scrollTo(habitacion);
                lblResultado.setText(datosHabitacion(habitacion));
                return;
            }
        }

        lblResultado.setText("La habitación " + numero + " no existe.");
    }

    private String texto(Element habitacion, String etiqueta) {
        return habitacion.getElementsByTagName(etiqueta).item(0).getTextContent();
    }

    private String estado(Element habitacion) {
        String estado = habitacion.getAttribute("estado");
        if (estado.equals("libre")) {
            return "disponible";
        }
        return estado;
    }

    private String datosHabitacion(Element habitacion) {
        // Usamos el mismo método para el listado y la búsqueda.
        String estado = habitacion.getAttribute("estado");
        if (estado.equals("libre")) {
            estado = "disponible";
        }

        return "Habitación " + habitacion.getAttribute("numero") + " - " + estado
                + "\nTipo: " + habitacion.getElementsByTagName("tipo").item(0).getTextContent()
                + " | Capacidad: " + habitacion.getElementsByTagName("capacidad").item(0).getTextContent()
                + "\nPrecio por noche: "
                + habitacion.getElementsByTagName("precioNoche").item(0).getTextContent() + " €";
    }
}
