package com.itblee.gui;

import com.itblee.General;
import com.itblee.Provider;
import com.itblee.config.Config;
import com.itblee.config.Language;
import com.itblee.service.TaiKhoanService;
import com.itblee.util.ValidateUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.itblee.util.FileUtil.createImageIcon;

public class FrameLogin extends JFrame {
	private boolean isHidePassword = true;
	private boolean isLogging = false;

	private final TaiKhoanService taiKhoanService = Provider.get(TaiKhoanService.class);

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
		setTitle("THE CROSSING COFFEE MANAGER");
		setIconImage(new ImageIcon("bin/images/logo.png").getImage());
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
			JLabel lbUsername = new JLabel(Language.LOGIN_LABEL_USERNAME);
			lbUsername.setForeground(Color.white);
			lbUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			lbUsername.setLabelFor(txtUsername);
			loginPanel.add(lbUsername);
			lbUsername.setBounds(695, 255, 106, 19);

			//---- lbPassword ----
			JLabel lbPassword = new JLabel(Language.LOGIN_LABEL_PASSWORD);
			lbPassword.setForeground(Color.white);
			lbPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			lbPassword.setLabelFor(txtPassword);
			loginPanel.add(lbPassword);
			lbPassword.setBounds(695, 325, 106, 19);

			//---- lbEye ----
			int lbEyeSize = 16;
			lbEye = new JLabel();
			lbEye.setIcon(createImageIcon("bin/images/components/eye-w.png", lbEyeSize, lbEyeSize));
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
			lbTitleYear.setBounds(635, 140, 150, 36);

			//---- lbTitleFirst ----
			JLabel lbTitleFirst = new JLabel("THE CROSSING");
			lbTitleFirst.setForeground(Color.white);
			lbTitleFirst.setFont(new Font("Papyrus", Font.PLAIN, 17));
			loginPanel.add(lbTitleFirst);
			lbTitleFirst.setBounds(454, 106, 318, 36);

			//---- lbTitleSecond ----
			JLabel lbTitleSecond = new JLabel("COFFEE");
			lbTitleSecond.setForeground(Color.white);
			lbTitleSecond.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
			loginPanel.add(lbTitleSecond);
			lbTitleSecond.setBounds(633, 105, 300, 36);

			//---- lbLogo ----
			int logoWidth = 160;
			int logoHeight = 144;
			JLabel lbLogo = new JLabel();
			lbLogo.setIcon(createImageIcon("bin/images/logo-w.png", logoWidth, logoHeight));
			loginPanel.add(lbLogo);
			lbLogo.setBounds(775, 65, logoWidth, logoHeight);

			//---- btnLogin ----
			btnLogin = new JButton(Language.LOGIN_BUTTON_TEXT);
			btnLogin.setBackground(Color.white);
			btnLogin.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
			btnLogin.setFocusPainted(false);
			btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnLogin.setBorder(null);
			btnLogin.setForeground(new Color(37, 37, 37));
			loginPanel.add(btnLogin);
			btnLogin.setBounds(775, 450, 160, 45);
			btnLogin.addActionListener(e -> onClickBtnLogin());
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
			if (General.password != null) {
				txtPassword.setText(General.password);
				txtPassword.setForeground(Color.white);
			} else {
				txtPassword.setText(Language.LOGIN_PASSWORD_PLACEHOLDER);
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
					if (String.valueOf(txtPassword.getPassword()).equals(Language.LOGIN_PASSWORD_PLACEHOLDER)) {
						txtPassword.setText("");
						txtPassword.setForeground(Color.white);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					txtPassword.setBorder(new EmptyBorder(0, 10, 0, 25));
					if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
						txtPassword.setForeground(Color.darkGray);
						txtPassword.setText(Language.LOGIN_PASSWORD_PLACEHOLDER);
					}
				}
			});

			//---- txtUsername ----
			txtUsername = new JTextField();
			if (General.username != null) {
				txtUsername.setText(General.username);
				txtUsername.setForeground(Color.white);
			} else {
				txtUsername.setText(Language.LOGIN_USERNAME_PLACEHOLDER);
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
					if (txtUsername.getText().equals(Language.LOGIN_USERNAME_PLACEHOLDER)) {
						txtUsername.setText("");
						txtUsername.setForeground(Color.white);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					txtUsername.setBorder(new EmptyBorder(0, 10, 0, 25));
					if (txtUsername.getText().isEmpty()) {
						txtUsername.setForeground(Color.darkGray);
						txtUsername.setText(Language.LOGIN_USERNAME_PLACEHOLDER);
					}
				}
			});

			//---- cbRemember ----
			cbRemember = new JCheckBox(" " + Language.LOGIN_CHECKBOX_REMEMBER_ME);
			cbRemember.setSelected(General.isRemember);
			cbRemember.setContentAreaFilled(false);
			cbRemember.setForeground(Color.white);
			cbRemember.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
			cbRemember.setCursor(new Cursor(Cursor.HAND_CURSOR));
			loginPanel.add(cbRemember);
			cbRemember.setBounds(680, 390, 170, 20);

			//---- btnExit ----
			int btnExitSize = 24;
			JLabel btnExit = new JLabel();
			btnExit.setIcon(createImageIcon("bin/images/components/exit-w.png", btnExitSize, btnExitSize));
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
			btnMinimize.setIcon(createImageIcon("bin/images/components/minimize-w.png", btnMinimizeSize, btnMinimizeSize));
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
		lbBackground.setIcon(createImageIcon("bin/images/login/background2.gif", bgWidth, bgHeight));
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
			lbEye.setIcon(createImageIcon("bin/images/components/eye-w-close.png", lbEye.getWidth(), lbEye.getHeight()));
			txtPassword.setEchoChar((char) 0);
		} else {
			lbEye.setIcon(createImageIcon("bin/images/components/eye-w.png", lbEye.getWidth(), lbEye.getHeight()));
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
		new Thread(() -> {
			try {
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				if (!ValidateUtil.isValidUsername(username) || !ValidateUtil.isValidPassword(password))
					throw new Exception(Language.LOGIN_INVALID_INPUT);
				if (taiKhoanService.login(username, password)) {
					Thread.sleep(1000);
					loginSuccess(username, password);
				} else
					throw new Exception(Language.LOGIN_ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				loginFail(e.getMessage());
			}
		}).start();
	}

	private void loginSuccess(String username, String password) {
		if (General.isRemember != cbRemember.isSelected()) {
			General.isRemember = cbRemember.isSelected();
			if (General.isRemember) {
				General.username = username;
				General.password = password;
			} else {
				General.username = null;
				General.password = null;
			}
			Config.save();
		} else if (General.isRemember
				&& (!General.username.equals(username) || !General.password.equals(password))) {
			General.username = username;
			General.password = password;
			Config.save();
		}
		animatedLogin();
	}

	private void loginFail(String message) {
		isLogging = false;
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		btnLogin.setText(Language.LOGIN_BUTTON_TEXT);
		txtUsername.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(0, 10, 0, 25),
				new MatteBorder(0,0,2,0, new Color(235, 64, 52))));
		txtPassword.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(0, 10, 0, 25),
				new MatteBorder(0,0,2,0, new Color(235, 64, 52))));
		setEnableForm(true);
		JOptionPane.showMessageDialog(getContentPane(), "Đăng nhập thất bại!\n" + (message == null ? "" : message), Language.LOGIN_ERROR, JOptionPane.ERROR_MESSAGE);
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
		new Thread(() -> {
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
		}).start();
	}

	private void animatedLogin() {
		lbTransition.setIcon(createImageIcon("bin/images/login/transition.gif", getWidth(), getHeight()));
		lbTransition.setBounds(0, 0, getWidth(), getHeight());
		new Thread(() -> {
			try {
				Thread.sleep(2200);
			} catch (InterruptedException ignored) {}
			General.frame = new FrameLayout();
			EventQueue.invokeLater(() -> {
				General.frame.setVisible(true);
				General.frame.toBack();
			});
			try {
				Thread.sleep(400);
				dispose();
				General.frame.requestFocusInWindow();
			} catch (InterruptedException ignored) {}
		}).start();
	}

	private JLabel lbTransition;
	private JLabel lbEye;
	private JButton btnLogin;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private JCheckBox cbRemember;
}
