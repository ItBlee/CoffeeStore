package GUI;

import BUS.Interfaces.ITaiKhoanBUS;
import BUS.TaiKhoanBUS;
import Utils.FileHandler;
import Utils.General;
import Utils.Validator;

import static Utils.FileHandler.createImageIcon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class FrameLogin extends JFrame {
	private boolean isHidePassword = true;
	private boolean isLogging = false;

	public FrameLogin() {
		initFrame();
		initComponents();
		getRootPane().setDefaultButton(btnLogin);
	}

	private void initFrame() {
		setSize(1080, 575);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon("images/logo.png").getImage());
		setTitle("THE CROSSING COFFEE MANAGER");
	}

	private void initComponents() {
		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== loginPanel ========
		JPanel loginPanel = new JPanel();
		{
			loginPanel.setOpaque(false);
			loginPanel.setLayout(null);

			//---- lbTransition ----
			lbTransition = new JLabel();
			loginPanel.add(lbTransition);

			//---- lbUsername ----
			JLabel lbUsername = new JLabel("Tên đăng nhập");
			lbUsername.setForeground(Color.white);
			lbUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			lbUsername.setLabelFor(txtUsername);
			loginPanel.add(lbUsername);
			lbUsername.setBounds(695, 255, 106, 19);

			//---- lbPassword ----
			JLabel lbPassword = new JLabel("Mật khẩu");
			lbPassword.setForeground(Color.white);
			lbPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			lbPassword.setLabelFor(txtPassword);
			loginPanel.add(lbPassword);
			lbPassword.setBounds(695, 325, 106, 19);

			//---- lbLogo ----
			int logoWidth = 160;
			int logoHeight = 144;
			JLabel lbLogo = new JLabel();
			lbLogo.setIcon(createImageIcon("images/logo-w.png", logoWidth, logoHeight));
			loginPanel.add(lbLogo);
			lbLogo.setBounds(775, 65, logoWidth, logoHeight);

			//---- lbEye ----
			int lbEyeSize = 16;
			lbEye = new JLabel();
			lbEye.setIcon(createImageIcon("images/components/eye-w.png", lbEyeSize, lbEyeSize));
			lbEye.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(lbEye);
			lbEye.setBounds(1000, 350, lbEyeSize, lbEyeSize);
			lbEye.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClickLbEye();
				}
			});

			//---- lbTitleSince ----
			JLabel lbTitleSince = new JLabel("SINCE");
			lbTitleSince.setForeground(Color.white);
			lbTitleSince.setFont(new Font("Papyrus", Font.PLAIN, 13));
			loginPanel.add(lbTitleSince);
			lbTitleSince.setBounds(572, 141, 81, 36);

			//---- lbTitleYear ----
			JLabel lbTitleYear = new JLabel("1999");
			lbTitleYear.setForeground(Color.white);
			lbTitleYear.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
			loginPanel.add(lbTitleYear);
			lbTitleYear.setBounds(635, 140, 97, 36);

			//---- lbTitleFirst ----
			JLabel lbTitleFirst = new JLabel("THE CROSSING");
			lbTitleFirst.setForeground(Color.white);
			lbTitleFirst.setFont(new Font("Papyrus", Font.PLAIN, 17));
			loginPanel.add(lbTitleFirst);
			lbTitleFirst.setBounds(454, 106, 218, 36);

			//---- lbTitleSecond ----
			JLabel lbTitleSecond = new JLabel("COFFEE");
			lbTitleSecond.setForeground(Color.white);
			lbTitleSecond.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
			loginPanel.add(lbTitleSecond);
			lbTitleSecond.setBounds(new Rectangle(new Point(633, 105), lbTitleSecond.getPreferredSize()));

			//---- btnLogin ----
			btnLogin = new JButton("Đăng Nhập");
			btnLogin.setBackground(Color.white);
			btnLogin.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
			btnLogin.setFocusPainted(false);
			btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnLogin.setBorder(null);
			btnLogin.setForeground(new Color(37, 37, 37));
			loginPanel.add(btnLogin);
			btnLogin.setBounds(775, 450, 160, 45);
			btnLogin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClickBtnLogin();
				}
			});
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

			//---- txtPassword ----
			txtPassword = new JPasswordField();
			if (General.USER_PASSWORD != null) {
				txtPassword.setText(General.USER_PASSWORD);
				txtPassword.setForeground(Color.white);
			} else {
				txtPassword.setText("Nhập mật khẩu");
				txtPassword.setForeground(Color.darkGray);
			}
			txtPassword.setBorder(new EmptyBorder(0, 10, 0, 25));
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
			txtUsername = new JTextField();
			if (General.USER_USERNAME != null) {
				txtUsername.setText(General.USER_USERNAME);
				txtUsername.setForeground(Color.white);
			} else {
				txtUsername.setText("Nhập tài khoản");
				txtUsername.setForeground(Color.darkGray);
			}
			txtUsername.setBorder(new EmptyBorder(0, 10, 0, 25));
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
			cbRemember = new JCheckBox(" Duy trì đăng nhập");
			cbRemember.setSelected(General.USER_IS_REMEMBER);
			cbRemember.setContentAreaFilled(false);
			cbRemember.setForeground(Color.white);
			cbRemember.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			cbRemember.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(cbRemember);
			cbRemember.setBounds(680, 390, 170, 20);

			//---- btnExit ----
			int btnExitSize = 24;
			JLabel btnExit = new JLabel();
			btnExit.setIcon(createImageIcon("images/components/exit-w.png", btnExitSize, btnExitSize));
			btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(btnExit);
			btnExit.setBounds(1045, 10, btnExitSize, btnExitSize);
			btnExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClickBtnExit();
				}
			});

			//---- btnMinimize ----
			int btnMinimizeSize = 24;
			JLabel btnMinimize = new JLabel();
			btnMinimize.setIcon(createImageIcon("images/components/minimize-w.png", btnMinimizeSize, btnMinimizeSize));
			btnMinimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(btnMinimize);
			btnMinimize.setBounds(1010, 5, btnMinimizeSize + 8, btnMinimizeSize + 8);
			btnMinimize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClickBtnMinimize();
				}
			});

			//======== bgLoginPanel ========
			JPanel bgLoginPanel = new JPanel();
			bgLoginPanel.setBackground(new Color(37, 37, 37));
			bgLoginPanel.setLayout(null);
			loginPanel.add(bgLoginPanel);
			bgLoginPanel.setBounds(630, 0, getWidth() - 630, getHeight());
		}
		contentPane.add(loginPanel);
		loginPanel.setBounds(0, 0, getWidth(), getHeight());

		//---- lbBackground ----
		int bgWidth = 1080;
		int bgHeight = 607;
		JLabel lbBackground = new JLabel();
		lbBackground.setIcon(createImageIcon("images/login/background2.gif", bgWidth, bgHeight));
		contentPane.add(lbBackground);
		lbBackground.setBounds(-70, -30, bgWidth, bgHeight);
	}

	private void onClickBtnExit() {
		dispose();
		System.exit(0);
	}

	private void onClickBtnMinimize() {
		setState(Frame.ICONIFIED);
	}

	private void onClickLbEye() {
		if (isHidePassword) {
			lbEye.setIcon(createImageIcon("images/components/eye-w-close.png", lbEye.getWidth(), lbEye.getHeight()));
			txtPassword.setEchoChar((char) 0);
		} else {
			lbEye.setIcon(createImageIcon("images/components/eye-w.png", lbEye.getWidth(), lbEye.getHeight()));
			txtPassword.setEchoChar('*');
		}
		isHidePassword = !isHidePassword;
	}

	private void onClickBtnLogin() {
		setEnableForm(false);
		animatedSubmit();
		login();
	}

	private void login() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ITaiKhoanBUS taiKhoanBUS = new TaiKhoanBUS();
					String username = txtUsername.getText();
					String password = String.valueOf(txtPassword.getPassword());
					if (!Validator.isValidUsername(username) || !Validator.isValidPassword(password))
						throw new Exception("Tài khoản hoặc Mật khẩu không hợp lệ !");
					if (taiKhoanBUS.login(username, password)) {
						Thread.sleep(1000);
						loginSuccess(username, password);
					} else
						throw new Exception("Không thể đăng nhập !");
				} catch (Exception e) {
					e.printStackTrace();
					loginFail(e.getMessage());
				}
			}
		}).start();
	}

	private void loginSuccess(String username, String password) {
		if (General.USER_IS_REMEMBER != cbRemember.isSelected()) {
			General.USER_IS_REMEMBER = cbRemember.isSelected();
			if (General.USER_IS_REMEMBER) {
				General.USER_USERNAME = username;
				General.USER_PASSWORD = password;
			} else {
				General.USER_USERNAME = null;
				General.USER_PASSWORD = null;
			}
			FileHandler.exportConfig();
		} else if (General.USER_IS_REMEMBER
				&& (!General.USER_USERNAME.equals(username) || !General.USER_PASSWORD.equals(password))) {
			General.USER_USERNAME = username;
			General.USER_PASSWORD = password;
			FileHandler.exportConfig();
		}
		animatedLogin();
	}

	private void loginFail(String message) {
		isLogging = false;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		btnLogin.setText("Đăng Nhập");
		txtUsername.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(0, 10, 0, 25),
				new MatteBorder(0,0,2,0, new Color(235, 64, 52))));
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(0, 10, 0, 25),
				new MatteBorder(0,0,2,0, new Color(235, 64, 52))));
		setEnableForm(true);
		JOptionPane.showMessageDialog(getContentPane(), message, "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
	}

	private void setEnableForm(boolean isEnable) {
		btnLogin.setEnabled(isEnable);
		txtUsername.setEnabled(isEnable);
		txtPassword.setEnabled(isEnable);
		cbRemember.setEnabled(isEnable);
	}

	private void animatedSubmit() {
		isLogging = true;
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		new Thread(new Runnable() {
			@SuppressWarnings("BusyWait")
			@Override
			public void run() {
				int i = 1;
				while (isLogging) {
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

	private void animatedLogin() {
		lbTransition.setIcon(createImageIcon("images/login/transition.gif", getWidth(), getHeight()));
		lbTransition.setBounds(0, 0, getWidth(), getHeight());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2200);
				} catch (InterruptedException ignored) {}
				JFrame frame = new FrameLayout();
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						frame.setVisible(true);
						frame.toBack();
					}
				});
				try {
					Thread.sleep(200);
					dispose();
					frame.requestFocusInWindow();
				} catch (InterruptedException ignored) {}
			}
		}).start();
	}

	private JLabel lbTransition;
	private JLabel lbEye;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private JCheckBox cbRemember;
}
