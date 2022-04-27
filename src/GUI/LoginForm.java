package GUI;

import Services.Interfaces.ITaiKhoanBUS;
import Services.TaiKhoanBUS;
import Utils.FileHandler;
import Utils.General;
import Utils.Validator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LoginForm extends JFrame {
	private static boolean HIDE_PASSWORD = true;
	private static boolean IS_LOGGING = false;

	public LoginForm() {
		initComponents();

		setSize(1080, 575);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon("images/logo.png").getImage());
		setTitle("THE CROSSING COFFEE MANAGER");
	}

	private void initComponents() {
		lbTransition = new JLabel();
		loginPanel = new JPanel();
		btnExit = new JLabel();
		btnMinimize = new JLabel();
		lbUsername = new JLabel();
		lbPassword = new JLabel();
		lbLogo = new JLabel();
		lbEye = new JLabel();
		lbTitleSince = new JLabel();
		lbTitleYear = new JLabel();
		lbTitleFirst = new JLabel();
		lbTitleSecond = new JLabel();
		btnLogin = new JButton();
		txtPassword = new JPasswordField();
		txtUsername = new JTextField();
		cbRemember = new JCheckBox();
		shapePanel = new JPanel();
		lbBackground = new JLabel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- lbTransition ----
		loginPanel.add(lbTransition);
		lbTransition.setBounds(0, 0, 1080, 575);
		//======== loginPanel ========
		{
			loginPanel.setOpaque(false);
			loginPanel.setLayout(null);

			//---- btnExit ----
			btnExit.setIcon(new ImageIcon("images/components/close-w.png"));
			loginPanel.add(btnExit);
			btnExit.setBounds(new Rectangle(new Point(1045, 10), btnExit.getPreferredSize()));
			btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClick_BtnExit();
				}
			});

			//---- btnMinimize ----
			btnMinimize.setIcon(new ImageIcon("images/components/minimize-w.png"));
			loginPanel.add(btnMinimize);
			btnMinimize.setBounds(1010, 5, 32, 32);
			btnMinimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnMinimize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClick_BtnMinimize();
				}
			});

			//---- lbUsername ----
			lbUsername.setText("Tên đăng nhập");
			lbUsername.setForeground(Color.white);
			lbUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			lbUsername.setLabelFor(txtUsername);
			loginPanel.add(lbUsername);
			lbUsername.setBounds(new Rectangle(new Point(695, 255), lbUsername.getPreferredSize()));

			//---- lbPassword ----
			lbPassword.setText("Mật khẩu");
			lbPassword.setForeground(Color.white);
			lbPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			lbPassword.setLabelFor(txtPassword);
			loginPanel.add(lbPassword);
			lbPassword.setBounds(695, 325, 106, 19);

			//---- lbLogo ----
			lbLogo.setIcon(new ImageIcon("images/logo-small.png"));
			loginPanel.add(lbLogo);
			lbLogo.setBounds(new Rectangle(new Point(775, 65), lbLogo.getPreferredSize()));

			//---- label1 ----
			lbEye.setIcon(new ImageIcon("images/components/eye-w.png"));
			lbEye.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(lbEye);
			lbEye.setBounds(new Rectangle(new Point(1000, 350), lbEye.getPreferredSize()));
			lbEye.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClick_LbEye();
				}
			});

			//---- lbTitleSince ----
			lbTitleSince.setText("SINCE");
			lbTitleSince.setForeground(Color.white);
			lbTitleSince.setFont(new Font("Papyrus", Font.PLAIN, 13));
			loginPanel.add(lbTitleSince);
			lbTitleSince.setBounds(572, 141, 81, 36);

			//---- lbTitleYear ----
			lbTitleYear.setText("1999");
			lbTitleYear.setForeground(Color.white);
			lbTitleYear.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
			loginPanel.add(lbTitleYear);
			lbTitleYear.setBounds(635, 140, 97, 36);

			//---- lbTitleFirst ----
			lbTitleFirst.setText("THE CROSSING");
			lbTitleFirst.setForeground(Color.white);
			lbTitleFirst.setFont(new Font("Papyrus", Font.PLAIN, 17));
			loginPanel.add(lbTitleFirst);
			lbTitleFirst.setBounds(454, 106, 218, 36);

			//---- lbTitleSecond ----
			lbTitleSecond.setText("COFFEE");
			lbTitleSecond.setForeground(Color.white);
			lbTitleSecond.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
			loginPanel.add(lbTitleSecond);
			lbTitleSecond.setBounds(new Rectangle(new Point(633, 105), lbTitleSecond.getPreferredSize()));

			//---- btnLogin ----
			btnLogin.setText("Đăng Nhập");
			btnLogin.setBackground(Color.white);
			btnLogin.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
			btnLogin.setFocusPainted(false);
			btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnLogin.setBorder(null);
			btnLogin.setForeground(new Color(22, 22, 22));
			loginPanel.add(btnLogin);
			btnLogin.setBounds(775, 450, 160, 45);
			btnLogin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					btnLogin.setBackground(Color.darkGray);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					btnLogin.setBackground(Color.lightGray);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnLogin.setBackground(Color.white);
				}
			});
			btnLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClick_BtnLogin();
				}
			});

			//---- txtPassword ----
			txtPassword.setBorder(new EmptyBorder(0, 10, 0, 25));
			if (General.USER_PASSWORD != null) {
				txtPassword.setText(General.USER_PASSWORD);
				txtPassword.setForeground(Color.white);
			}
			else {
				txtPassword.setText("Nhập mật khẩu");
				txtPassword.setForeground(Color.darkGray);
			}
			txtPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			txtPassword.setBackground(new Color(22, 22, 22));
			loginPanel.add(txtPassword);
			txtPassword.setBounds(680, 335, 350, 45);
			txtPassword.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					txtPassword.setBorder(BorderFactory.createCompoundBorder(
							new EmptyBorder(0, 10, 0, 25),
							new MatteBorder(0,0,2,0, Color.white)));
					if (String.valueOf(txtPassword.getPassword()).equals("Nhập mật khẩu")) {
						txtPassword.setText("");
						txtPassword.setForeground(Color.white);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					txtPassword.setBorder(new EmptyBorder(0, 10, 0, 25));
					if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
						txtPassword.setForeground(Color.darkGray);
						txtPassword.setText("Nhập mật khẩu");
					}
				}
			});

			//---- txtUsername ----
			txtUsername.setBorder(new EmptyBorder(0, 10, 0, 25));
			if (General.USER_USERNAME != null) {
				txtUsername.setText(General.USER_USERNAME);
				txtUsername.setForeground(Color.white);
			}
			else {
				txtUsername.setText("Nhập tài khoản");
				txtUsername.setForeground(Color.darkGray);
			}
			txtUsername.setBackground(new Color(22, 22, 22));
			txtUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			loginPanel.add(txtUsername);
			txtUsername.setBounds(680, 265, 350, 45);
			txtUsername.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					txtUsername.setBorder(BorderFactory.createCompoundBorder(
							new EmptyBorder(0, 10, 0, 25),
							new MatteBorder(0,0,2,0, Color.white)));
					if (txtUsername.getText().equals("Nhập tài khoản")) {
						txtUsername.setText("");
						txtUsername.setForeground(Color.white);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					txtUsername.setBorder(new EmptyBorder(0, 10, 0, 25));
					if (txtUsername.getText().isEmpty()) {
						txtUsername.setForeground(Color.darkGray);
						txtUsername.setText("Nhập tài khoản");
					}
				}
			});

			//---- cbRemember ----
			cbRemember.setSelected(General.USER_IS_REMEMBER);
			cbRemember.setText("Duy trì đăng nhập");
			cbRemember.setContentAreaFilled(false);
			cbRemember.setForeground(Color.white);
			cbRemember.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			cbRemember.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(cbRemember);
			cbRemember.setBounds(new Rectangle(new Point(680, 390), cbRemember.getPreferredSize()));

			//======== shapePanel ========
			{
				shapePanel.setBackground(new Color(37, 37, 37));
				shapePanel.setLayout(null);

				{
					// compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < shapePanel.getComponentCount(); i++) {
						Rectangle bounds = shapePanel.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = shapePanel.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					shapePanel.setMinimumSize(preferredSize);
					shapePanel.setPreferredSize(preferredSize);
				}
			}
			loginPanel.add(shapePanel);
			shapePanel.setBounds(630, 0, 450, 575);

			{
				// compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < loginPanel.getComponentCount(); i++) {
					Rectangle bounds = loginPanel.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = loginPanel.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				loginPanel.setMinimumSize(preferredSize);
				loginPanel.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(loginPanel);
		loginPanel.setBounds(0, 0, 1080, 575);

		//---- lbBackground ----
		lbBackground.setIcon(new ImageIcon("images/login/background2.gif"));
		contentPane.add(lbBackground);
		lbBackground.setBounds(new Rectangle(new Point(-70, -30), lbBackground.getPreferredSize()));

		{
			// compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		getContentPane().requestFocusInWindow();
	}

	private void onClick_BtnExit() {
		dispose();
		System.exit(0);
	}

	private void onClick_BtnMinimize() {
		setState(Frame.ICONIFIED);
	}

	private void onClick_LbEye() {
		if (HIDE_PASSWORD) {
			lbEye.setIcon(new ImageIcon("images/components/eye-w-close.png"));
			txtPassword.setEchoChar((char)0);
		} else {
			lbEye.setIcon(new ImageIcon("images/components/eye-w.png"));
			txtPassword.setEchoChar('*');
		}
		HIDE_PASSWORD = !HIDE_PASSWORD;
	}

	private void onClick_BtnLogin() {
		setEnableForm(false);
		displayLoggingProcess();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ITaiKhoanBUS bus = new TaiKhoanBUS();
					String username = txtUsername.getText();
					String password = String.valueOf(txtPassword.getPassword());
					if (!Validator.isValidUsername(username) || !Validator.isValidPassword(password))
						throw new Exception("Tài khoản hoặc Mật khẩu không hợp lệ !");
					if (bus.login(username, password)) {
						Thread.sleep(1000);
						loginSuccess();
					}
					else throw new Exception("Không thể đăng nhập !");
				} catch (Exception e) {
					e.printStackTrace();
					loginFail(e.getMessage());
				}
			}
		}).start();
	}

	private void loginSuccess() {
		General.USER_IS_REMEMBER = cbRemember.isSelected();
		if (General.USER_IS_REMEMBER)
			FileHandler.exportConfig();
		changeToManagerForm();
	}

	private void loginFail(String message) {
		IS_LOGGING = false;
		btnLogin.setText("Đăng Nhập");
		setEnableForm(true);
		txtUsername.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(0, 10, 0, 25),
				new MatteBorder(0,0,2,0, new Color(235, 64, 52))));
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(0, 10, 0, 25),
				new MatteBorder(0,0,2,0, new Color(235, 64, 52))));
		JOptionPane.showMessageDialog(getContentPane(), message, "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
	}

	private void changeToManagerForm() {
		lbTransition.setIcon(new ImageIcon("images/login/transition.gif"));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException ignored) {}
				Main.frame.dispose();
			}
		}).start();
	}

	private void setEnableForm(boolean b) {
		btnLogin.setEnabled(b);
		txtUsername.setEnabled(b);
		txtPassword.setEnabled(b);
		cbRemember.setEnabled(b);
	}

	private void displayLoggingProcess() {
		IS_LOGGING = true;
		new Thread(new Runnable() {
			@SuppressWarnings("BusyWait")
			@Override
			public void run() {
				int i = 1;
				while (IS_LOGGING) {
					btnLogin.setText(("" + i++)
							.replace("1", "●")
							.replace("2", "●●")
							.replace("3", "●●●")
							.replace("4", "●●●●")
							.replace("5", "●●●●●"));
					if (i == 5)
						i = 1;
					try {
						Thread.sleep(200);
					} catch (InterruptedException ignored) {}
				}
			}
		}).start();
	}

	private JLabel lbTransition;
	private JLabel btnExit;
	private JLabel btnMinimize;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JLabel lbLogo;
	private JLabel lbEye;
	private JLabel lbTitleSince;
	private JLabel lbTitleYear;
	private JLabel lbTitleFirst;
	private JLabel lbTitleSecond;
	private JLabel lbBackground;

	private JButton btnLogin;

	private JPasswordField txtPassword;
	private JTextField txtUsername;

	private JCheckBox cbRemember;

	private JPanel shapePanel;
	private JPanel loginPanel;

}
