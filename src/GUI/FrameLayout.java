package GUI;

import GUI.Form.*;
import GUI.components.JChooserDialog;
import GUI.components.JMovableJFrame;
import GUI.components.MenuItem;
import Utils.FileHandler;
import Utils.General;
import Utils.Themes;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes.FlatIJLookAndFeelInfo;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class FrameLayout extends JMovableJFrame {
	private boolean isLocked = false;
	private ArrayList<MenuItem> menu = null;
	private FlatIJLookAndFeelInfo currentTheme;
	private Font currentFont;
	private JPanel currentPanel;

	public FrameLayout() {
		initFrame();
		initMenuButton();
		initComponents();
		initTheme();
		setSelectedButton(menu.get(0).getButton());
		setFormPanel(menu.get(0).getForm());
	}

	private void initFrame() {
		setSize(1190, 880);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon("images/logo.png").getImage());
		setTitle("THE CROSSING COFFEE MANAGER");
	}

	private void initTheme() {
		Themes.setupThemeByInfo(General.THEME_INFO);
		this.currentTheme = General.THEME_INFO;
		this.currentFont = General.THEME_FONT;
	}

	private void initMenuButton() {
		if (menu == null)
			menu = new ArrayList<MenuItem>();

		MenuItem itemHoaDon = new MenuItem();
		itemHoaDon.setCode("HD");
		itemHoaDon.setToolTipText("Hóa đơn");
		itemHoaDon.setKeyBlind(KeyEvent.VK_1);
		itemHoaDon.setIcon("images/components/HD.png");
		itemHoaDon.setIconHover("images/components/HD-hover.gif");
		itemHoaDon.setForm(new FormHoaDon());
		menu.add(itemHoaDon);

		MenuItem itemSanPham = new MenuItem();
		itemSanPham.setCode("SP");
		itemSanPham.setToolTipText("Sản phẩm");
		itemSanPham.setKeyBlind(KeyEvent.VK_2);
		itemSanPham.setIcon("images/components/SP.png");
		itemSanPham.setIconHover("images/components/SP-hover.gif");
		itemSanPham.setForm(new FormSanPham());
		menu.add(itemSanPham);

		MenuItem itemPhieuNhap = new MenuItem();
		itemPhieuNhap.setCode("PN");
		itemPhieuNhap.setToolTipText("Phiếu nhập");
		itemPhieuNhap.setKeyBlind(KeyEvent.VK_3);
		itemPhieuNhap.setIcon("images/components/PN.png");
		itemPhieuNhap.setIconHover("images/components/PN-hover.gif");
		itemPhieuNhap.setForm(new FormPhieuNhap());
		menu.add(itemPhieuNhap);

		MenuItem itemNCC = new MenuItem();
		itemNCC.setCode("NCC");
		itemNCC.setToolTipText("Nguồn cung");
		itemNCC.setKeyBlind(KeyEvent.VK_4);
		itemNCC.setIcon("images/components/NCC.png");
		itemNCC.setIconHover("images/components/NCC-hover.gif");
		itemNCC.setForm(new FormNCC());
		menu.add(itemNCC);

		MenuItem itemKhachHang = new MenuItem();
		itemKhachHang.setCode("KH");
		itemKhachHang.setToolTipText("Khách hàng");
		itemKhachHang.setKeyBlind(KeyEvent.VK_5);
		itemKhachHang.setIcon("images/components/KH.png");
		itemKhachHang.setIconHover("images/components/KH-hover.gif");
		itemKhachHang.setForm(new FormKhachHang());
		menu.add(itemKhachHang);

		MenuItem itemKhuyenMai = new MenuItem();
		itemKhuyenMai.setCode("KM");
		itemKhuyenMai.setToolTipText("Khuyến mãi");
		itemKhuyenMai.setKeyBlind(KeyEvent.VK_6);
		itemKhuyenMai.setIcon("images/components/KM.png");
		itemKhuyenMai.setIconHover("images/components/KM-hover.gif");
		itemKhuyenMai.setForm(new FormKhuyenMai());
		menu.add(itemKhuyenMai);

		MenuItem itemThongKe = new MenuItem();
		itemThongKe.setCode("TK");
		itemThongKe.setToolTipText("Thống kê");
		itemThongKe.setKeyBlind(KeyEvent.VK_7);
		itemThongKe.setIcon("images/components/TK.png");
		itemThongKe.setIconHover("images/components/TK-hover.gif");
		itemThongKe.setForm(new FormThongKe());
		menu.add(itemThongKe);

		MenuItem itemExcel = new MenuItem();
		itemExcel.setCode("excel");
		itemExcel.setToolTipText("Xuất/Nhập");
		itemExcel.setKeyBlind(KeyEvent.VK_8);
		itemExcel.setIcon("images/components/excel.png");
		itemExcel.setIconHover("images/components/excel-hover.gif");
		itemExcel.setForm(new FormExcel());
		menu.add(itemExcel);

		MenuItem itemTaiKhoan = new MenuItem();
		itemTaiKhoan.setCode("NV");
		itemTaiKhoan.setToolTipText("Tài khoản");
		itemTaiKhoan.setKeyBlind(KeyEvent.VK_9);
		itemTaiKhoan.setIcon("images/components/NV.png");
		itemTaiKhoan.setIconHover("images/components/NV-hover.gif");
		itemTaiKhoan.setForm(new FormTaiKhoan());
		menu.add(itemTaiKhoan);
	}

	private void initComponents() {
		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== formLayoutPanel ========
		formLayoutPanel = new JPanel();
		formLayoutPanel.setOpaque(false);
		formLayoutPanel.setLayout(null);
		contentPane.add(formLayoutPanel);
		formLayoutPanel.setBounds(185, 58, 1000, 817);

		//======== lbIntro ========
		int introWidth = getWidth();
		int introHeight = getHeight();
		lbIntro = new JLabel();
		lbIntro.setIcon(createImageIcon("images/components/layout_intro.gif", introWidth, introHeight));
		contentPane.add(lbIntro);
		lbIntro.setBounds(0,0, introWidth, introHeight);

		//======== coverIntroPanel ========
		coverIntroPanel = new JPanel();
		coverIntroPanel.setBackground(new Color(37, 37, 37));
		contentPane.add(coverIntroPanel);
		coverIntroPanel.setBounds(lbIntro.getBounds());

		//======== mainPanel ========
		menuPanel = new JPanel();
		{
			menuPanel.setBackground(new Color(37, 37, 37));
			menuPanel.setBorder(new MatteBorder(0, 0, 0, 5, UIManager.getColor("Button.disabledBackground")));
			menuPanel.setLayout(null);

			//---- settingPanel ----
			settingPanel = new JPanel();
			{
				settingPanel.setBackground(menuPanel.getBackground());
				settingPanel.setLayout(null);
				settingPanel.setVisible(false);

				JLabel lbSettingTitle = new JLabel("CÀI ĐẶT");
				lbSettingTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lbSettingTitle.setForeground(Color.white);
				lbSettingTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
				settingPanel.add(lbSettingTitle);
				lbSettingTitle.setBounds(5, 10, 170, 20);

				JLabel lbThemeChooser = new JLabel("Giao diện: ");
				lbThemeChooser.setForeground(Color.white);
				lbThemeChooser.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				settingPanel.add(lbThemeChooser);
				lbThemeChooser.setBounds(5, 70, 170, 20);

				FlatIJLookAndFeelInfo[] themeInfoList = Themes.getThemeInfoList();
				String[] themeList = new String[themeInfoList.length];
				for (int i = 0; i < themeInfoList.length; i++)
					themeList[i] = themeInfoList[i].getName();

				cbThemeChooser = new JComboBox<String>(themeList);
				cbThemeChooser.setSelectedIndex(Themes.getIndexOfTheme(General.THEME_INFO));
				cbThemeChooser.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						int selectedIndex = cbThemeChooser.getSelectedIndex();
						onSelectedItemCBThemeChooser(themeInfoList[selectedIndex]);
					}
				});
				settingPanel.add(cbThemeChooser);
				cbThemeChooser.setBounds(5, 100, 170 , 30);

				JLabel lbFontChooser = new JLabel("Kiểu chữ: ");
				lbFontChooser.setForeground(Color.white);
				lbFontChooser.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				settingPanel.add(lbFontChooser);
				lbFontChooser.setBounds(5, 160, 170, 20);

				JButton btnFontChooser = new JButton("BẢNG CHỮ");
				btnFontChooser.setFocusPainted(false);
				btnFontChooser.setBackground(new Color(37,37,37));
				btnFontChooser.setForeground(Color.white);
				btnFontChooser.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btnFontChooser.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickBtnFontChooser();
					}
				});
				settingPanel.add(btnFontChooser);
				btnFontChooser.setBounds(5, 190, 170, 40);

				JButton btnSaveSetting = new JButton("LƯU THAY ĐỔI");
				btnSaveSetting.setFocusPainted(false);
				btnSaveSetting.setBackground(Color.white);
				btnSaveSetting.setForeground(new Color(37,37,37));
				btnSaveSetting.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btnSaveSetting.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickBtnSaveSetting();
					}
				});
				settingPanel.add(btnSaveSetting);
				btnSaveSetting.setBounds(5, 520, 170, 40);

				JButton btnBackSetting = new JButton("HỦY THAY ĐỔI");
				btnBackSetting.setFocusPainted(false);
				btnBackSetting.setBackground(UIManager.getColor("Button.disabledBackground"));
				btnBackSetting.setForeground(Color.white);
				btnBackSetting.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btnBackSetting.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickBtnBackSetting();
					}
				});
				settingPanel.add(btnBackSetting);
				btnBackSetting.setBounds(5, 575, 170, 40);
			}
			menuPanel.add(settingPanel);
			settingPanel.setBounds(0, 120, 180, 640);

			infoPanel = new JPanel();
			//---- infoPanel ----
			{
				infoPanel.setBackground(menuPanel.getBackground());
				infoPanel.setLayout(null);
				infoPanel.setVisible(false);

				JLabel lbInfoTitle = new JLabel("THÔNG TIN");
				lbInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lbInfoTitle.setForeground(Color.white);
				lbInfoTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
				infoPanel.add(lbInfoTitle);
				lbInfoTitle.setBounds(5, 10, 170, 20);

				JTextArea info = new JTextArea("a");
				info.setText("Mã NV: NV1" + "\n\n" +
						"Mã TK: TK1" + "\n\n" +
						"Họ tên: Trần Long Tuấn Vũ" + "\n\n" +
						"Ngày sinh: 12/34/1234" + "\n\n" +
						"SDT: 0123456789" + "\n\n" +
						"Email: tranlongtuanvu@gmail.com" + "\n\n" +
						"Giới tính: Nam");
				info.setEditable(false);
				info.setCursor(null);
				info.setOpaque(false);
				info.setFocusable(false);
				info.setWrapStyleWord(true);
				info.setLineWrap(true);
				info.setBorder(new EmptyBorder(5, 5, 5, 5));
				info.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				info.setForeground(Color.white);
				info.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				infoPanel.add(info);
				info.setBounds(0, 65, 170, 500);

				JButton btnBackInfo = new JButton("QUAY LẠI");
				btnBackInfo.setFocusPainted(false);
				btnBackInfo.setBackground(Color.white);
				btnBackInfo.setForeground(new Color(37,37,37));
				btnBackInfo.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btnBackInfo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickLBAvatar();
					}
				});
				infoPanel.add(btnBackInfo);
				btnBackInfo.setBounds(5, 575, 170, 40);
			}
			menuPanel.add(infoPanel);
			infoPanel.setBounds(0, 120, 180, 640);

			//---- lbAvatar ----
			int avatarSize = 128;
			lbAvatar = new JLabel();
			lbAvatar.setIcon(createImageIcon("images/components/avatar-holder.png", avatarSize, avatarSize));
			lbAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuPanel.add(lbAvatar);
			lbAvatar.setBounds(30, 0, avatarSize, avatarSize);
			lbAvatar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClickLBAvatar();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					lbAvatar.setIcon(createImageIcon("images/components/avatar-holder-hover.gif", avatarSize, avatarSize));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (!infoPanel.isVisible())
						lbAvatar.setIcon(createImageIcon("images/components/avatar-holder.png", avatarSize, avatarSize));
				}
			});

			//---- btnMenu ----
			int currentY = MenuItem.START_Y;
			for (MenuItem item : menu) {
				JButton btn = new JButton();
				btn.setIcon(item.getIcon());
				btn.setToolTipText(item.getToolTipText());
				btn.setBackground(Color.white);
				btn.setBorder(new MatteBorder(0, 5, 0, 0, UIManager.getColor("Button.disabledBackground")));
				btn.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btn.setFocusPainted(false);
				btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				menuPanel.add(btn);
				btn.setBounds(MenuItem.DEFAULT_X, currentY, MenuItem.ITEM_BUTTON_SIZE, MenuItem.ITEM_BUTTON_SIZE);
				setButtonKeyBlind(btn, item.getKeyBlind());
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setSelectedButton(btn);
						setFormPanel(item.getForm());
					}
				});
				btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						if (btn.isEnabled())
							btn.setIcon(item.getIconHover());
					}
					@Override
					public void mouseExited(MouseEvent e) {
						btn.setIcon(item.getIcon());
					}
				});
				item.setButton(btn);
				currentY += MenuItem.ITEM_BUTTON_SIZE;
			}
			menu.get(0).getButton().setBorder(new MatteBorder(5, 5, 0, 0, UIManager.getColor("Button.disabledBackground")));
			menu.get(menu.size()-1).getButton().setBorder(new MatteBorder(0, 5, 5, 0, UIManager.getColor("Button.disabledBackground")));

			int miniBtnSize = 40;

			//---- btnContact ----
			btnContact = new JButton();
			btnContact.setToolTipText("Liên hệ");
			btnContact.setIcon(createImageIcon("images/components/cup.png" , miniBtnSize, miniBtnSize));
			btnContact.setBackground(Color.white);
			btnContact.setContentAreaFilled(false);
			btnContact.setBorderPainted(false);
			menuPanel.add(btnContact);
			btnContact.setBounds(20, 760, miniBtnSize+8, miniBtnSize+8);
			btnContact.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClickBtnContact();
				}
			});
			btnContact.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnContact.setContentAreaFilled(true);
					btnContact.setIcon(createImageIcon("images/components/contact-hover.gif" , miniBtnSize, miniBtnSize));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnContact.setContentAreaFilled(false);
					btnContact.setIcon(createImageIcon("images/components/cup.png" , miniBtnSize, miniBtnSize));
				}
			});

			//---- btnLogout ----
			btnLogout = new JButton();
			btnLogout.setToolTipText("Đăng xuất");
			btnLogout.setIcon(createImageIcon("images/components/logout.png" , miniBtnSize, miniBtnSize));
			btnLogout.setBackground(Color.white);
			btnLogout.setContentAreaFilled(false);
			btnLogout.setBorderPainted(false);
			btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuPanel.add(btnLogout);
			btnLogout.setBounds(btnContact.getX() + 50, 760, miniBtnSize+8, miniBtnSize+8);
			btnLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClickBtnLogout();
				}
			});
			btnLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnLogout.setContentAreaFilled(true);
					btnLogout.setIcon(createImageIcon("images/components/logout-hover.png" , miniBtnSize, miniBtnSize));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnLogout.setContentAreaFilled(false);
					btnLogout.setIcon(createImageIcon("images/components/logout.png" , miniBtnSize, miniBtnSize));
				}
			});

			//---- btnSetting ----
			btnSetting = new JButton();
			btnSetting.setToolTipText("Cài đặt");
			btnSetting.setIcon(createImageIcon("images/components/setting.png" , miniBtnSize, miniBtnSize));
			btnSetting.setBackground(Color.white);
			btnSetting.setContentAreaFilled(false);
			btnSetting.setBorderPainted(false);
			btnSetting.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuPanel.add(btnSetting);
			btnSetting.setBounds(btnLogout.getX() + 50, 760, miniBtnSize+8, miniBtnSize+8);
			btnSetting.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onClickBtnSetting();
				}
			});
			btnSetting.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnSetting.setContentAreaFilled(true);
					btnSetting.setIcon(createImageIcon("images/components/setting-hover.gif" , miniBtnSize, miniBtnSize));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					if (!settingPanel.isVisible()) {
						btnSetting.setContentAreaFilled(false);
						btnSetting.setIcon(createImageIcon("images/components/setting.png" , miniBtnSize, miniBtnSize));
					}
				}
			});

			//---- lbWaterDropPanel ----
			JPanel endDropCover = new JPanel();
			endDropCover.setBackground(menuPanel.getBackground());
			endDropCover.setFocusable(false);
			menuPanel.add(endDropCover);
			endDropCover.setBounds(0, 767, 180, 62);

			int lbWaterWeight = 105;
			int lbWaterHeight = 579;
			lbWaterDropPanel = new JLabel();
			lbWaterDropPanel.setIcon(createImageIcon("images/components/waterDropLoop.gif", lbWaterWeight, lbWaterHeight));
			lbWaterDropPanel.setFocusable(false);
			lbWaterDropPanel.setVisible(false);
			menuPanel.add(lbWaterDropPanel);
			lbWaterDropPanel.setBounds(-3, 190, lbWaterWeight, lbWaterHeight);
		}
		contentPane.add(menuPanel);
		menuPanel.setBounds(0, formLayoutPanel.getY()-5, 185, formLayoutPanel.getHeight());

		JPanel topBorderFormLayoutPanel = new JPanel();
		topBorderFormLayoutPanel.setBackground(UIManager.getColor("Button.disabledBackground"));
		contentPane.add(topBorderFormLayoutPanel);
		topBorderFormLayoutPanel.setBounds(185, formLayoutPanel.getY()-5, formLayoutPanel.getWidth(), 5);

		JPanel backgroundPanel = new JPanel();
		//======== backgroundPanel ========
		{
			backgroundPanel.setBackground(new Color(37, 37, 37));
			backgroundPanel.setLayout(null);

			//---- btnExit ----
			int btnExitSize = 24;
			JLabel btnExit = new JLabel();
			btnExit.setIcon(createImageIcon("images/components/exit-w.png", btnExitSize, btnExitSize));
			btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			backgroundPanel.add(btnExit);
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
			backgroundPanel.add(btnMinimize);
			btnMinimize.setBounds(1010, 5, btnMinimizeSize + 8, btnMinimizeSize + 8);
			btnMinimize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onClickBtnMinimize();
				}
			});

			//---- lbTitle ----
			JLabel lbTitle = new JLabel();
			lbTitle.setText("THE CROSSING COFFEE MANAGER");
			lbTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
			lbTitle.setForeground(Color.white);
			backgroundPanel.add(lbTitle);
			lbTitle.setBounds(new Rectangle(new Point(20, 10), lbTitle.getPreferredSize()));
		}
		contentPane.add(backgroundPanel);
		backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		animatedIntro();
	}

	private void onClickBtnExit() {
		dispose();
		System.exit(0);
	}

	private void onClickBtnMinimize() {
		setState(Frame.ICONIFIED);
	}

	private void onClickBtnLogout() {
		int confirm = JOptionPane.showConfirmDialog(getContentPane(), "Bạn muốn đăng xuất ?", "Đăng xuất", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirm == JOptionPane.YES_OPTION) {
			General.CURRENT_USER = null;
			dispose();
			JFrame frame = new FrameLogin();
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					frame.setVisible(true);
					frame.requestFocusInWindow();
				}
			});
		}
	}

	private void onClickBtnContact() {
		JOptionPane.showMessageDialog(getContentPane(), "Liên hệ", "Kỹ thuật viên: Nguyễn Văn A\nSĐT: 0913222222", JOptionPane.INFORMATION_MESSAGE);
	}

	private void onClickBtnSetting() {
		if (infoPanel.isVisible())
			infoPanel.setVisible(false);
		int iconSize;
		if (!settingPanel.isVisible()) {
			btnSetting.setContentAreaFilled(true);
			iconSize = 32;
			btnSetting.setIcon(createImageIcon("images/components/setting-hover.gif", iconSize, iconSize));
		}
		else {
			btnSetting.setContentAreaFilled(false);
			iconSize = 16;
			btnSetting.setIcon(createImageIcon("images/components/setting.png", iconSize, iconSize));
		}
		settingPanel.setVisible(!settingPanel.isVisible());
		setVisibleMenuButton(!settingPanel.isVisible());
	}

	private void onClickBtnFontChooser() {
		Font newFont = JChooserDialog.showFontChooser(this);
		if (newFont != null) {
			currentFont = newFont;
			setFormFont(currentFont);
		}
	}

	private void onClickBtnSaveSetting() {
		int save = JOptionPane.showConfirmDialog(getContentPane(), "Bạn có chắc muốn lưu thay đổi ?", "Cài đặt", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (save == JOptionPane.OK_OPTION) {
			General.THEME_INFO = currentTheme;
			General.THEME_FONT = currentFont;
			FileHandler.exportConfig();
			JOptionPane.showMessageDialog(getContentPane(), "Hoàn thành", "Đã lưu cài đặt !", JOptionPane.INFORMATION_MESSAGE);
		}
		btnSetting.doClick();
	}

	private void onClickBtnBackSetting() {
		setFormFont(General.THEME_FONT);
		Themes.setupThemeByInfo(General.THEME_INFO);
		cbThemeChooser.setSelectedIndex(Themes.getIndexOfTheme(General.THEME_INFO));
		btnSetting.doClick();
	}

	private void onClickLBAvatar() {
		if (settingPanel.isVisible())
			btnSetting.doClick();
		infoPanel.setVisible(!infoPanel.isVisible());
		setVisibleMenuButton(!infoPanel.isVisible());
	}

	private void onSelectedItemCBThemeChooser(FlatIJLookAndFeelInfo themeInfo) {
		if (themeInfo == null)
			return;
		currentTheme = themeInfo;
		Themes.setupThemeByInfo(currentTheme);
		try {
			setFormPanel(currentPanel.getClass().getConstructor().newInstance());
		} catch (Exception ignored) {}
	}

	private void setButtonKeyBlind(JButton btn, int key) {
		KeyStroke keyStroke = KeyStroke.getKeyStroke(key, KeyEvent.ALT_DOWN_MASK);
		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = btn.getInputMap(condition);
		ActionMap actionMap = btn.getActionMap();
		inputMap.put(keyStroke, keyStroke.toString());
		actionMap.put(keyStroke.toString(), new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btn.doClick();
			}
		});
	}

	private void setSelectedButton(JButton btn) {
		if (isLocked)
			return;
		JButton oldSelectedBtn = null;
		for (Component c : menuPanel.getComponents())
			if (c instanceof JButton && c != btn && !c.isEnabled()) {
				oldSelectedBtn = ((JButton) c);
				break;
			}
		animatedSwapSelectedButton(btn, oldSelectedBtn);
	}

	private void setVisibleMenuButton(boolean isVisible) {
		for (MenuItem item : menu) {
			if (item.getButton() != null)
				item.getButton().setVisible(isVisible);
		}
	}

	private void setFormPanel(JPanel newPanel) {
		if (isLocked)
			return;
		if (currentPanel != null)
			formLayoutPanel.remove(currentPanel);
		currentPanel = newPanel;
		currentPanel.setOpaque(true);
		currentPanel.setBounds(0, 0, formLayoutPanel.getWidth(), formLayoutPanel.getHeight());
		formLayoutPanel.add(currentPanel);
		formLayoutPanel.revalidate();
		formLayoutPanel.repaint();
		if (currentFont != null)
			setFormFont(currentFont);
	}

	private void setFormFont(Font font) {
		for (Component c : currentPanel.getComponents())
			c.setFont(font);
	}

	private ImageIcon createImageIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
		if (icon.getIconHeight() != height || icon.getIconWidth() != width) {
			Image scale = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
			icon = new ImageIcon(scale);
		}
		return icon;
	}

	private void animatedSwapSelectedButton(JButton enableBtn, JButton disableBtn) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				isLocked = true;
				lbWaterDropPanel.setVisible(false);
				enableBtn.setEnabled(false);
				if (disableBtn != null) {
					disableBtn.setEnabled(true);
					disableBtn.setText("");
				}
				int time = 0;
				int pixelEachTime = 1;
				int loopTimes = enableBtn.getX() / pixelEachTime;
				while (time < loopTimes) {
					try {
						Thread.sleep(3);
						enableBtn.setBounds(enableBtn.getX() - pixelEachTime, enableBtn.getY(), enableBtn.getWidth() + pixelEachTime, enableBtn.getHeight());
						disableBtn.setBounds(disableBtn.getX() + pixelEachTime, disableBtn.getY(), disableBtn.getWidth() - pixelEachTime, disableBtn.getHeight());
					} catch (Exception ignored) {}
					time += 1;
				}
				enableBtn.setText(" " + enableBtn.getToolTipText().toUpperCase());
				lbWaterDropPanel.setBounds(-3, enableBtn.getY() + enableBtn.getHeight(), lbWaterDropPanel.getWidth(), lbWaterDropPanel.getHeight());
				lbWaterDropPanel.setVisible(true);
				isLocked = false;
			}
		}).start();
	}

	private void animatedIntro() {
		new Thread(new Runnable() {
			@SuppressWarnings("BusyWait")
			@Override
			public void run() {
				try {
					currentPanel.setBounds(0, 0, 1000, 0);
					menuPanel.setVisible(false);
					Thread.sleep(1300);
					remove(coverIntroPanel);
					//animated form appear
					{
						while (currentPanel.getHeight() < menuPanel.getHeight()) {
							currentPanel.setBounds(0, 0, 1000, currentPanel.getHeight() + 3);
							Thread.sleep(1);
						}
						currentPanel.setBounds(0, 0, 1000, formLayoutPanel.getHeight());
					}
					Thread.sleep(2430);
				} catch (InterruptedException ignored) {}
				//remove intro panel after finish
				menuPanel.setVisible(true);
				lbIntro.setIcon(null);
				lbWaterDropPanel.setVisible(true);
				remove(lbIntro);
				revalidate();
				repaint();
			}
		}).start();
	}

	private JLabel lbIntro;
	private JLabel lbAvatar;
	private JLabel lbWaterDropPanel;

	private JButton btnLogout;
	private JButton btnContact;
	private JButton btnSetting;

	private JPanel settingPanel;
	private JPanel infoPanel;
	private JPanel formLayoutPanel;
	private JPanel menuPanel;
	private JPanel coverIntroPanel;

	private JComboBox<String> cbThemeChooser;
}
