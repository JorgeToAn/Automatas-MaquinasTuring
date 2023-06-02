import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;

public class App extends JFrame {
    private JPanel contentPane;
	private JTextField rutaField;
	private JTextField entradaField;

    private MaquinaTuring maquina;
    private TextFile archivoMaquina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    /**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 416);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloLabel = new JLabel("Maquinas de Turing");
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tituloLabel.setBounds(125, 11, 240, 37);
		contentPane.add(tituloLabel);
		
		rutaField = new JTextField();
		rutaField.setBackground(new Color(192, 192, 192));
		rutaField.setBounds(10, 59, 424, 20);
		contentPane.add(rutaField);
		rutaField.setColumns(10);

        entradaField = new JTextField();
		entradaField.setBackground(new Color(192, 192, 192));
		entradaField.setBounds(10, 90, 424, 20);
		contentPane.add(entradaField);
		entradaField.setColumns(10);

        JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(192, 192, 192));
		textArea.setBounds(10, 121, 424, 203);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
		contentPane.add(textArea);
		
		JButton archivoButton = new JButton("");
		archivoButton.setIcon(new ImageIcon(".\\static\\file-and-folder.png"));
		archivoButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File(".\\ejemplos"));
		        int seleccion = fileChooser.showOpenDialog(App.this);
		        if (seleccion == JFileChooser.APPROVE_OPTION) {
		            // Se ha seleccionado un archivo
		            java.io.File archivo = fileChooser.getSelectedFile();
		            rutaField.setText(archivo.getAbsolutePath());
		        }
		    }
		});
		archivoButton.setBounds(444, 53, 41, 37);
		contentPane.add(archivoButton);
		
		JButton comprobarButton = new JButton("Comprobar");
		comprobarButton.setBackground(new Color(255, 255, 255));
		comprobarButton.setBounds(165, 343, 107, 23);
        comprobarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    archivoMaquina = new TextFile(rutaField.getText());
                    maquina = new MaquinaTuring(archivoMaquina);

                    try {
                        String entrada = entradaField.getText();
                        Resultado resultado = maquina.procesar(entrada);
                        
						if(resultado.getAceptado()) {
							textArea.setText("ACEPTADO\n" + resultado.getSalida());
						} else {
							textArea.setText("RECHAZADO\n" + resultado.getSalida());
						}
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(contentPane, "Ha ocurrido un error durante la ejecucion de la maquina turing.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        textArea.setText(ex.getMessage());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, "Ha ocurrido un error con el archivo de la maquina turing.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    textArea.setText(ex.getMessage());
                }
            }
        });
		contentPane.add(comprobarButton);
	}
}
