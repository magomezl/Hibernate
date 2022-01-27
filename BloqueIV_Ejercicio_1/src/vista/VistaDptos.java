package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaDptos extends JFrame {

	public JPanel contentPane;
	public JTextField txtDptoNum;
	public JTextField txtNombre;
	public JTextField txtLocalidad;
	public JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaDptos frame = new VistaDptos();
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
	public VistaDptos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtDptoNum = new JTextField();
		txtDptoNum.setText("N\u00FAmero de Departamento");
		txtDptoNum.setBounds(56, 36, 186, 20);
		panel.add(txtDptoNum);
		txtDptoNum.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setColumns(10);
		txtNombre.setBounds(56, 67, 186, 20);
		panel.add(txtNombre);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setText("Localidad");
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(56, 98, 186, 20);
		panel.add(txtLocalidad);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(56, 129, 89, 23);
		panel.add(btnGuardar);
	}
}
