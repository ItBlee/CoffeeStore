package GUI;

import GUI.Form.*;
import GUI.common.Language;
import GUI.common.Theme;
import GUI.components.*;
import Utils.FileHandler;
import Utils.General;
import Utils.SystemConstant;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes.FlatIJLookAndFeelInfo;

import static Utils.FileHandler.createImageIcon;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class FrameLayout extends MovableJFrame {
	private boolean isLocked = false;
	private boolean isDisplayIntro = true;
	private ArrayList<Category> categories = null;

	public Category currentItem;
	private Font currentFont;
	private Language currentLanguage;
	private FlatIJLookAndFeelInfo currentTheme;

	public FrameLayout() {
		initFrame();
		initMenuButton();
		this.currentItem = categories.get(0);
		this.currentTheme = Theme.getSystemThemeInfo();
		this.currentFont = Theme.getSystemThemeFont();
		this.currentLanguage = Language.getSystemLanguage();
		initComponents();
		initTheme();
		currentItem.getButton().doClick();
	}

	private void initFrame() {
		setSize(1190, 880);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("THE CROSSING COFFEE MANAGER");
		setIconImage(new ImageIcon("bin/images/logo.png").getImage());
	}

	private void initTheme() {
		Theme.setupThemeByInfo(Theme.getSystemThemeInfo());
		setFormFont(Theme.getSystemThemeFont());
	}

	private void initMenuButton() {
		categories = new ArrayList<Category>();

		if (General.CURRENT_ROLE.getQuyenNV() != null &&
				General.CURRENT_ROLE.getQuyenNV().getQuyenDoc() == 1) {
			Category itemTaiKhoan = new Category();
			itemTaiKhoan.setCode("NV");
			itemTaiKhoan.setToolTipText(Language.LAYOUT_BUTTON_NV);
			itemTaiKhoan.setKeyBlind(KeyEvent.VK_9);
			itemTaiKhoan.setIcon("bin/images/components/NV.png");
			itemTaiKhoan.setIconHover("bin/images/components/NV-hover.gif");
			itemTaiKhoan.setFormClassName(FormNhanSu.class.getName());
			categories.add(itemTaiKhoan);
		}

		if (General.CURRENT_ROLE.getQuyenSP() != null &&
				General.CURRENT_ROLE.getQuyenSP().getQuyenDoc() == 1) {
			Category itemSanPham = new Category();
			itemSanPham.setCode("SP");
			itemSanPham.setToolTipText(Language.LAYOUT_BUTTON_SP);
			itemSanPham.setKeyBlind(KeyEvent.VK_2);
			itemSanPham.setIcon("bin/images/components/SP.png");
			itemSanPham.setIconHover("bin/images/components/SP-hover.gif");
			itemSanPham.setFormClassName(FormSanPham.class.getName());
			categories.add(itemSanPham);
		}

		if (General.CURRENT_ROLE.getQuyenPN() != null &&
				General.CURRENT_ROLE.getQuyenPN().getQuyenDoc() == 1) {
			Category itemPhieuNhap = new Category();
			itemPhieuNhap.setCode("PN");
			itemPhieuNhap.setToolTipText(Language.LAYOUT_BUTTON_PN);
			itemPhieuNhap.setKeyBlind(KeyEvent.VK_3);
			itemPhieuNhap.setIcon("bin/images/components/PN.png");
			itemPhieuNhap.setIconHover("bin/images/components/PN-hover.gif");
			itemPhieuNhap.setFormClassName(FormPhieuNhap.class.getName());
			categories.add(itemPhieuNhap);
		}

		if (General.CURRENT_ROLE.getQuyenNCC() != null &&
				General.CURRENT_ROLE.getQuyenNCC().getQuyenDoc() == 1) {
			Category itemNCC = new Category();
			itemNCC.setCode("NCC");
			itemNCC.setToolTipText(Language.LAYOUT_BUTTON_NCC);
			itemNCC.setKeyBlind(KeyEvent.VK_4);
			itemNCC.setIcon("bin/images/components/NCC.png");
			itemNCC.setIconHover("bin/images/components/NCC-hover.gif");
			itemNCC.setFormClassName(FormNCC.class.getName());
			categories.add(itemNCC);
		}

		if (General.CURRENT_ROLE.getQuyenKH() != null &&
				General.CURRENT_ROLE.getQuyenKH().getQuyenDoc() == 1) {
			Category itemKhachHang = new Category();
			itemKhachHang.setCode("KH");
			itemKhachHang.setToolTipText(Language.LAYOUT_BUTTON_KH);
			itemKhachHang.setKeyBlind(KeyEvent.VK_5);
			itemKhachHang.setIcon("bin/images/components/KH.png");
			itemKhachHang.setIconHover("bin/images/components/KH-hover.gif");
			itemKhachHang.setFormClassName(FormKhachHang.class.getName());
			categories.add(itemKhachHang);
		}

		if (General.CURRENT_ROLE.getQuyenKM() != null &&
				General.CURRENT_ROLE.getQuyenKM().getQuyenDoc() == 1) {
			Category itemKhuyenMai = new Category();
			itemKhuyenMai.setCode("KM");
			itemKhuyenMai.setToolTipText(Language.LAYOUT_BUTTON_KM);
			itemKhuyenMai.setKeyBlind(KeyEvent.VK_6);
			itemKhuyenMai.setIcon("bin/images/components/KM.png");
			itemKhuyenMai.setIconHover("bin/images/components/KM-hover.gif");
			itemKhuyenMai.setFormClassName(FormKhuyenMai.class.getName());
			categories.add(itemKhuyenMai);
		}

		if (General.CURRENT_ROLE.getQuyenTK() != null &&
				General.CURRENT_ROLE.getQuyenTK().getQuyenDoc() == 1) {
			Category itemThongKe = new Category();
			itemThongKe.setCode("TK");
			itemThongKe.setToolTipText(Language.LAYOUT_BUTTON_TK);
			itemThongKe.setKeyBlind(KeyEvent.VK_7);
			itemThongKe.setIcon("bin/images/components/TK.png");
			itemThongKe.setIconHover("bin/images/components/TK-hover.gif");
			itemThongKe.setFormClassName(FormThongKe.class.getName());
			categories.add(itemThongKe);
		}

		if (General.CURRENT_ROLE.getQuyenExcel() != null &&
				General.CURRENT_ROLE.getQuyenExcel().getQuyenDoc() == 1) {
			Category itemExcel = new Category();
			itemExcel.setCode("excel");
			itemExcel.setToolTipText(Language.LAYOUT_BUTTON_EXCEL);
			itemExcel.setKeyBlind(KeyEvent.VK_8);
			itemExcel.setIcon("bin/images/components/excel.png");
			itemExcel.setIconHover("bin/images/components/excel-hover.gif");
			itemExcel.setFormClassName(FormExcel.class.getName());
			categories.add(itemExcel);
		}

		if (General.CURRENT_ROLE.getQuyenHD() != null &&
				General.CURRENT_ROLE.getQuyenHD().getQuyenDoc() == 1) {
			Category itemHoaDon = new Category();
			itemHoaDon.setCode("HD");
			itemHoaDon.setToolTipText(Language.LAYOUT_BUTTON_HD);
			itemHoaDon.setKeyBlind(KeyEvent.VK_1);
			itemHoaDon.setIcon("bin/images/components/HD.png");
			itemHoaDon.setIconHover("bin/images/components/HD-hover.gif");
			itemHoaDon.setFormClassName(FormHoaDon.class.getName());
			categories.add(itemHoaDon);
		}
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

		if (isDisplayIntro) {
			//======== lbIntro ========
			int introWidth = getWidth();
			int introHeight = getHeight();
			lbIntro = new JLabel();
			lbIntro.setIcon(createImageIcon("bin/images/components/layout_intro.gif", introWidth, introHeight));
			contentPane.add(lbIntro);
			lbIntro.setBounds(0, 0, introWidth, introHeight);

			//======== coverIntroPanel ========
			coverIntroPanel = new JPanel();
			coverIntroPanel.setBackground(new Color(37, 37, 37));
			contentPane.add(coverIntroPanel);
			coverIntroPanel.setBounds(lbIntro.getBounds());
		}
		//======== mainPanel ========
		menuPanel = new JPanel();
		{
			menuPanel.setBackground(new Color(37, 37, 37));
			menuPanel.setBorder(new MatteBorder(0, 0, 0, 5, new Color(60,63,65)));
			menuPanel.setLayout(null);

			//---- settingPanel ----
			settingPanel = new JPanel();
			{
				settingPanel.setBackground(menuPanel.getBackground());
				settingPanel.setLayout(null);
				settingPanel.setVisible(false);

				JLabel lbSettingTitle = new JLabel(Language.LAYOUT_SETTING_TITLE);
				lbSettingTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lbSettingTitle.setForeground(Color.white);
				lbSettingTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
				settingPanel.add(lbSettingTitle);
				lbSettingTitle.setBounds(5, 10, 170, 20);

				JLabel lbThemeChooser = new JLabel(Language.LAYOUT_LABEL_THEME_CHOOSER);
				lbThemeChooser.setForeground(Color.white);
				lbThemeChooser.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				settingPanel.add(lbThemeChooser);
				lbThemeChooser.setBounds(5, 70, 170, 20);

				FlatIJLookAndFeelInfo[] themeInfoList = Theme.getThemeInfoList();
				String[] themeList = new String[themeInfoList.length];
				for (int i = 0; i < themeInfoList.length; i++)
					themeList[i] = themeInfoList[i].getName();

				cbThemeChooser = new JComboBox<String>(themeList);
				cbThemeChooser.setSelectedIndex(Theme.getIndexOf(currentTheme));
				cbThemeChooser.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						int selectedIndex = cbThemeChooser.getSelectedIndex();
						onSelectedItemCBThemeChooser(themeInfoList[selectedIndex]);
					}
				});
				settingPanel.add(cbThemeChooser);
				cbThemeChooser.setBounds(5, 100, 170 , 30);

				JLabel lbLanguageChooser = new JLabel(Language.LAYOUT_LABEL_LANGUAGE_CHOOSER);
				lbLanguageChooser.setForeground(Color.white);
				lbLanguageChooser.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				settingPanel.add(lbLanguageChooser);
				lbLanguageChooser.setBounds(5, 160, 170, 20);

				ArrayList<Language> languageList = Language.getLanguageList();
				String[] languageNameList = new String[languageList.size()];
				int index = 0;
				for (Language language : languageList) {
					languageNameList[index] = language.getName();
					index++;
				}

				cbLanguageChooser = new JComboBox<String>(languageNameList);
				cbLanguageChooser.setSelectedItem(currentLanguage.getName());
				cbLanguageChooser.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onSelectedItemCBLanguageChooser((languageNameList[cbLanguageChooser.getSelectedIndex()]));
					}
				});
				settingPanel.add(cbLanguageChooser);
				cbLanguageChooser.setBounds(5, 190, 170 , 30);

				JLabel lbFontChooser = new JLabel(Language.LAYOUT_LABEL_FONT_CHOOSER);
				lbFontChooser.setForeground(Color.white);
				lbFontChooser.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				settingPanel.add(lbFontChooser);
				lbFontChooser.setBounds(5, 250, 170, 20);

				JButton btnFontChooser = new JButton(Language.LAYOUT_BUTTON_FONT_CHOOSER);
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
				btnFontChooser.setBounds(5, 280, 170, 40);

				JButton btnSaveSetting = new JButton(Language.LAYOUT_SETTING_SAVE);
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

				JButton btnCancelSetting = new JButton(Language.LAYOUT_SETTING_SAVE_CANCEL);
				btnCancelSetting.setFocusPainted(false);
				btnCancelSetting.setBackground(new Color(60,63,65));
				btnCancelSetting.setForeground(Color.white);
				btnCancelSetting.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btnCancelSetting.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickBtnCancelSetting();
					}
				});
				settingPanel.add(btnCancelSetting);
				btnCancelSetting.setBounds(5, 575, 170, 40);
			}
			menuPanel.add(settingPanel);
			settingPanel.setBounds(0, 120, 180, 640);

			infoPanel = new JPanel();
			//---- infoPanel ----
			{
				infoPanel.setBackground(menuPanel.getBackground());
				infoPanel.setLayout(null);
				infoPanel.setVisible(false);

				JLabel lbInfoTitle = new JLabel(Language.LAYOUT_INFO_TITLE);
				lbInfoTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lbInfoTitle.setForeground(Color.white);
				lbInfoTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
				infoPanel.add(lbInfoTitle);
				lbInfoTitle.setBounds(5, 10, 170, 20);

				JTextArea info = new JTextArea();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				info.setText("Mã NV: " + General.CURRENT_USER.getMaNV() + "\n\n" +
						"Mã TK: TK" + General.CURRENT_USER.getMaTK() + "\n\n" +
						"Họ tên: " + General.CURRENT_USER.getHoTen() + "\n\n" +
						"Ngày sinh: " + formatter.format(General.CURRENT_USER.getNgaySinh()) + "\n\n" +
						"SDT: " + General.CURRENT_USER.getSDT() + "\n\n" +
						"Email: " + General.CURRENT_USER.getEmail() + "\n\n" +
						"Giới tính: " + (General.CURRENT_USER.getGioiTinh() == 1 ? "Nam" : "Nữ"));
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

				JButton btnBackInfo = new JButton(Language.LAYOUT_BUTTON_INFO_BACK);
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
			lbAvatar.setIcon(createImageIcon("bin/images/components/avatar-holder.png", avatarSize, avatarSize));
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
					lbAvatar.setIcon(createImageIcon("bin/images/components/avatar-holder-hover.gif", avatarSize, avatarSize));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (!infoPanel.isVisible())
						lbAvatar.setIcon(createImageIcon("bin/images/components/avatar-holder.png", avatarSize, avatarSize));
				}
			});

			//---- btnMenu ----
			int currentY = 120;
			int defaultX = 110;
			for (Category item : categories) {
				JButton btn = new JButton();
				btn.setIcon(item.getIcon());
				btn.setToolTipText(item.getToolTipText());
				btn.setBackground(Color.white);
				btn.setBorder(new MatteBorder(0, 5, 0, 0, new Color(60, 63, 65)));
				btn.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
				btn.setFocusPainted(false);
				btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				menuPanel.add(btn);
				btn.setBounds(defaultX, currentY, Category.ITEM_BUTTON_SIZE, Category.ITEM_BUTTON_SIZE);
				setButtonKeyBlind(btn, item.getKeyBlind());
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (!FrameLayout.this.isLocked) {
							setSelectedButton(btn);
							setFormPanel(item.getForm());
						}
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
				currentY += Category.ITEM_BUTTON_SIZE;
			}
			categories.get(0).getButton().setBorder(new MatteBorder(5, 5, 0, 0, new Color(60, 63, 65)));
			categories.get(categories.size()-1).getButton().setBorder(new MatteBorder(0, 5, 5, 0, new Color(60, 63, 65)));

			int miniBtnSize = 48;
			//---- btnContact ----
			int btnContactSize = 40;
			btnContact = new JButton();
			btnContact.setToolTipText(Language.LAYOUT_LABEL_CONTACT_TOOL_TIP_TEXT);
			btnContact.setIcon(createImageIcon("bin/images/components/cup.png" , btnContactSize, btnContactSize));
			btnContact.setBackground(Color.white);
			btnContact.setContentAreaFilled(false);
			btnContact.setBorderPainted(false);
			menuPanel.add(btnContact);
			btnContact.setBounds(20, 760, miniBtnSize, miniBtnSize);
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
					btnContact.setIcon(createImageIcon("bin/images/components/contact-hover.gif" , btnContactSize-4, btnContactSize-4));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnContact.setContentAreaFilled(false);
					btnContact.setIcon(createImageIcon("bin/images/components/cup.png" , btnContactSize, btnContactSize));
				}
			});

			//---- btnLogout ----
			int btnLogoutSize = 16;
			btnLogout = new JButton();
			btnLogout.setToolTipText(Language.LAYOUT_LABEL_LOGOUT_TOOL_TIP_TEXT);
			btnLogout.setIcon(createImageIcon("bin/images/components/logout.png" , btnLogoutSize, btnLogoutSize));
			btnLogout.setBackground(Color.white);
			btnLogout.setContentAreaFilled(false);
			btnLogout.setBorderPainted(false);
			btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuPanel.add(btnLogout);
			btnLogout.setBounds(btnContact.getX() + 50, 760, miniBtnSize, miniBtnSize);
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
					btnLogout.setIcon(createImageIcon("bin/images/components/logout-hover.png" , btnLogoutSize, btnLogoutSize));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					btnLogout.setContentAreaFilled(false);
					btnLogout.setIcon(createImageIcon("bin/images/components/logout.png" , btnLogoutSize, btnLogoutSize));
				}
			});

			//---- btnSetting ----
			int btnSettingSize = 16;
			btnSetting = new JButton();
			btnSetting.setToolTipText(Language.LAYOUT_LABEL_SETTING_TOOL_TIP_TEXT);
			btnSetting.setIcon(createImageIcon("bin/images/components/setting.png" , btnSettingSize, btnSettingSize));
			btnSetting.setBackground(Color.white);
			btnSetting.setContentAreaFilled(false);
			btnSetting.setBorderPainted(false);
			btnSetting.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuPanel.add(btnSetting);
			btnSetting.setBounds(btnLogout.getX() + 50, 760, miniBtnSize, miniBtnSize);
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
					btnSetting.setIcon(createImageIcon("bin/images/components/setting-hover.gif" , btnSettingSize*2, btnSettingSize*2));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					if (!settingPanel.isVisible()) {
						btnSetting.setContentAreaFilled(false);
						btnSetting.setIcon(createImageIcon("bin/images/components/setting.png" , btnSettingSize, btnSettingSize));
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
			lbWaterDropPanel.setIcon(createImageIcon("bin/images/components/waterDropLoop.gif", lbWaterWeight, lbWaterHeight));
			lbWaterDropPanel.setFocusable(false);
			lbWaterDropPanel.setVisible(false);
			menuPanel.add(lbWaterDropPanel);
			lbWaterDropPanel.setBounds(-3, 190, lbWaterWeight, lbWaterHeight);
		}
		contentPane.add(menuPanel);
		menuPanel.setBounds(0, formLayoutPanel.getY()-5, 185, formLayoutPanel.getHeight()+5);

		JPanel topBorderFormLayoutPanel = new JPanel();
		topBorderFormLayoutPanel.setBackground(new Color(60, 63, 65));
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
			btnExit.setIcon(createImageIcon("bin/images/components/exit-w.png", btnExitSize, btnExitSize));
			btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			backgroundPanel.add(btnExit);
			btnExit.setBounds(1160, 15, btnExitSize, btnExitSize);
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
			backgroundPanel.add(btnMinimize);
			btnMinimize.setBounds(1125, 10, btnMinimizeSize+8, btnMinimizeSize+8);
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
		if (isDisplayIntro)
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
		int confirm = JOptionPane.showConfirmDialog(getContentPane(), Language.LAYOUT_MESSAGE_LOGOUT, Language.LAYOUT_LABEL_LOGOUT_TOOL_TIP_TEXT, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirm == JOptionPane.YES_OPTION) {
			General.CURRENT_USER = null;
			General.importMapper(FileHandler.importConfig(SystemConstant.CONFIG_FILE_URL));
			Language.setup();
			Theme.setupDefault();
			JFrame frame = new FrameLogin();
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					frame.setVisible(true);
					frame.requestFocusInWindow();
					dispose();
				}
			});
		}
	}

	private void onClickBtnContact() {
		JOptionPane.showMessageDialog(getContentPane(), Language.LAYOUT_MESSAGE_CONTACT, Language.LAYOUT_LABEL_CONTACT_TOOL_TIP_TEXT, JOptionPane.INFORMATION_MESSAGE);
	}

	private void onClickBtnSetting() {
		if (infoPanel.isVisible())
			infoPanel.setVisible(false);
		int iconSize;
		if (!settingPanel.isVisible()) {
			iconSize = 32;
			btnSetting.setIcon(createImageIcon("bin/images/components/setting-hover.gif", iconSize, iconSize));
		}
		else {
			iconSize = 16;
			btnSetting.setIcon(createImageIcon("bin/images/components/setting.png", iconSize, iconSize));
		}
		btnSetting.setContentAreaFilled(!settingPanel.isVisible());
		settingPanel.setVisible(!settingPanel.isVisible());
		setVisibleMenuButton(!settingPanel.isVisible());
	}

	private void onClickBtnSaveSetting() {
		int save = JOptionPane.showConfirmDialog(getContentPane(), Language.LAYOUT_MESSAGE_SETTING_QUESTION, Language.LAYOUT_LABEL_SETTING_TOOL_TIP_TEXT, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (save == JOptionPane.OK_OPTION) {
			Language.setSystemLanguage(currentLanguage);
			Theme.setSystemThemeInfo(currentTheme);
			Theme.setSystemThemeFont(currentFont);
			FileHandler.exportConfig(General.exportMapper());
			JOptionPane.showMessageDialog(getContentPane(), Language.LAYOUT_SETTING_FINISH_TITLE, Language.LAYOUT_MESSAGE_SETTING_FINISH, JOptionPane.INFORMATION_MESSAGE);
		}
		btnSetting.doClick();
	}

	private void onClickBtnCancelSetting() {
		final int FONT_FLAG = 1;
		final int THEME_FLAG = 3;
		final int LANGUAGE_FLAG = 5;
		int repaintLevel = 0;

		if (!currentFont.equals(Theme.getSystemThemeFont())) {
			currentFont = Theme.getSystemThemeFont();
			repaintLevel += FONT_FLAG;
		}
		if (!currentTheme.getName().equals(Theme.getSystemThemeInfo().getName())) {
			currentTheme = Theme.getSystemThemeInfo();
			Theme.setupThemeByInfo(Theme.getSystemThemeInfo());
			repaintLevel += THEME_FLAG;
		}
		if (!currentLanguage.getDisplayName().equals(Language.getSystemLanguage().getDisplayName())) {
			currentLanguage = Language.getSystemLanguage();
			Language.importLanguage(Language.getSystemLanguage());
			repaintLevel += LANGUAGE_FLAG;
		}

		if (repaintLevel >= LANGUAGE_FLAG)
			repaintFrame();
		else repaintAllForm();
		btnSetting.doClick();
	}

	private void onClickLBAvatar() {
		if (settingPanel.isVisible())
			btnSetting.doClick();
		infoPanel.setVisible(!infoPanel.isVisible());
		setVisibleMenuButton(!infoPanel.isVisible());
	}

	private void onClickBtnFontChooser() {
		Font newFont = ChooserJDialog.showFontChooser(FrameLayout.this);
		if (newFont != null) {
			currentFont = newFont;
			setFormFont(currentFont);
		}
	}

	private void onSelectedItemCBThemeChooser(FlatIJLookAndFeelInfo themeInfo) {
		if (themeInfo == null || currentTheme.getName().equals(themeInfo.getName()))
			return;
		currentTheme = themeInfo;
		Theme.setupThemeByInfo(currentTheme);
		repaintAllForm();
	}

	@SuppressWarnings("BusyWait")
	private void onSelectedItemCBLanguageChooser(String languageName) {
		if (languageName == null || languageName.isBlank())
			return;
		Language language = Language.getLanguageByName(languageName);
		if (language == null || language.getDisplayName().equals(currentLanguage.getDisplayName()))
			return;
		currentLanguage = language;
		Language.importLanguage(currentLanguage);
		repaintFrame();
		try {
			while (true) {
				Thread.sleep(200);
				if (isEnabled()) {
					btnSetting.doClick();
					break;
				}
			}
		} catch (InterruptedException ignored) {}
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

	private void setSelectedButton(JButton newBtn) {
		if (newBtn == null)
			return;
		ArrayList<JButton> oldBtnList = new ArrayList<JButton>();
		for (Component c : menuPanel.getComponents())
			if (c instanceof JButton && c != newBtn && (!c.isEnabled() || c.getWidth() > Category.ITEM_BUTTON_SIZE)) {
				oldBtnList.add((JButton) c);
				break;
			}
		animatedSwapSelectedButton(newBtn, oldBtnList);
	}

	private void setVisibleMenuButton(boolean isVisible) {
		for (Category item : categories) {
			if (item.getButton() != null)
				item.getButton().setVisible(isVisible);
		}
	}

	private void setFormPanel(JPanel newPanel) {
		if (newPanel == null)
			return;
		formLayoutPanel.removeAll();
		for (Category item : categories) {
			if (item.getFormClassName().equals(newPanel.getClass().getName()))
				currentItem = item;
		}
		currentItem.getForm().setOpaque(true);
		currentItem.getForm().setBounds(0, 0, formLayoutPanel.getWidth(), formLayoutPanel.getHeight());
		formLayoutPanel.add(currentItem.getForm());
		formLayoutPanel.revalidate();
		formLayoutPanel.repaint();
	}

	private void changeFont(Component component, Font font) {
		if (component == null)
			return;
		component.setFont(font);
		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents())
				changeFont(child, font);
		}
	}

	private void setFormFont(Font font) {
		if (font == null)
			return;
		changeFont(currentItem.getForm(), font);
		for (Category item : categories)
			changeFont(item.getForm(), font);
	}

	private void repaintAllForm() {
		for (Category item : categories) {
			if (item.getRootForm() != null)
				item.renewForm();
		}
		setFormPanel(currentItem.getForm());
		setFormFont(currentFont);
	}

	private void repaintFrame() {
		setEnabled(false);
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		new Thread(new Runnable() {
			@Override
			public void run() {
				isDisplayIntro = false;
				initMenuButton();
				for (Category item : categories) {
					if (item.getCode().equals(currentItem.getCode())) {
						currentItem = item;
						break;
					}
				}
				getContentPane().removeAll();
				revalidate();
				repaint();
				Theme.setupDefault();
				initComponents();
				Theme.setupThemeByInfo(currentTheme);
				setFormFont(currentFont);
				setSelectedButton(currentItem.getButton());
				setFormPanel(currentItem.getForm());
				revalidate();
				repaint();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				setEnabled(true);
			}
		}).start();
	}

	private void animatedSwapSelectedButton(JButton enableBtn, ArrayList<JButton> disableBtnList) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				isLocked = true;
				lbWaterDropPanel.setVisible(false);
				for (JButton disableBtn : disableBtnList) {
					if (disableBtn != null) {
						disableBtn.setEnabled(true);
						disableBtn.setText("");
					}
				}
				enableBtn.setEnabled(false);
				int time = 0;
				int pixelEachTime = 1;
				int loopTimes = enableBtn.getX() / pixelEachTime;
				while (time < loopTimes) {
					try {
						Thread.sleep(3);
						enableBtn.setBounds(enableBtn.getX() - pixelEachTime, enableBtn.getY(), enableBtn.getWidth() + pixelEachTime, enableBtn.getHeight());
						for (JButton disableBtn : disableBtnList)
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
					currentItem.getForm().setBounds(0, 0, formLayoutPanel.getWidth(), 0);
					menuPanel.setVisible(false);
					Thread.sleep(1300);
					remove(coverIntroPanel);
					//animated form appear
					{
						while (currentItem.getForm().getHeight() < menuPanel.getHeight()) {
							currentItem.getForm().setBounds(0, 0, formLayoutPanel.getWidth(), currentItem.getForm().getHeight() + 6);
							Thread.sleep(1);
						}
						currentItem.getForm().setBounds(0, 0, formLayoutPanel.getWidth(), formLayoutPanel.getHeight());
					}
					Thread.sleep(1400);
					//remove intro panel after finish
					menuPanel.setVisible(true);
					Thread.sleep(200);
					lbIntro.setIcon(null);
					lbWaterDropPanel.setVisible(true);
				} catch (InterruptedException ignored) {}
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
	private JComboBox<String> cbLanguageChooser;
}
