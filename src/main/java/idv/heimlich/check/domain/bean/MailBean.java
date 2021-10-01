package idv.heimlich.check.domain.bean;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import idv.heimlich.check.common.mail.MailFace;

public class MailBean {

	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String CSMAIL2_TRADEVAN_COM_TW = "csmail2.tradevan.com.tw";
	private String[] to = null; // 收件人
	private String[] cc = null; // 副本收件人
	private String[] bcc = null; // 密送副本收件人
	private String from = ""; // 寄件人
	private String subject = ""; // 郵件主旨
	private String msgText = ""; // 郵件內容
	private String[] file = null; // 附加檔案

	// 建構子--開始

	public MailBean(final MailFace face) {
		this.to = face.to() == null ? null : this.toArray(face.to());
		this.from = face.from();
		this.subject = ""; //ApContext.getContext().getSetting("env") + face.subject();
		this.msgText = face.msg();
		this.cc = face.cc() == null ? null : this.toArray(face.cc());
		this.file = face.files() == null ? null : face.files();
	}

	/**
	 * 執行主程式
	 * 
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMail() throws MessagingException, UnsupportedEncodingException {
		final boolean sessionDebug = false;
		final Properties props = System.getProperties();
		props.put(MAIL_SMTP_HOST, CSMAIL2_TRADEVAN_COM_TW);
		final Session session = Session.getInstance(props, null);// Session.getDefaultInstance(props,
		session.setDebug(sessionDebug);

		// create a message
		final MimeMessage msg = new MimeMessage(session);
		int count = 0;
		// 設定收件人
		if (this.to != null && this.from.trim().length() != 0) {
			count = 0;
			for (int i = 0; i < this.to.length; i++) {
				if (this.to[i] != null && this.to[i].length() != 0) {
					count++;
				}
			}

			final InternetAddress[] address = new InternetAddress[count];

			for (int i = 0; i < count; i++) {
				if (this.to[i] != null && this.to[i].length() != 0) {
					address[i] = new InternetAddress(this.to[i]);
				}
			}

			msg.setRecipients(Message.RecipientType.TO, address);

			// 設定副本收件人
			if (this.cc != null) {
				count = 0;
				for (int i = 0; i < this.cc.length; i++) {
					if (this.cc[i] != null && this.cc[i].length() != 0) {
						count++;
					}
				}

				final InternetAddress[] ccaddress = new InternetAddress[count];

				for (int i = 0; i < count; i++) {
					if (this.cc[i] != null && this.cc[i].length() != 0) {
						ccaddress[i] = new InternetAddress(this.cc[i]);
					}
				}
				msg.setRecipients(Message.RecipientType.CC, ccaddress);

			}

			// 設定密件副本收件人
			if (this.bcc != null) {
				count = 0;
				for (int i = 0; i < this.bcc.length; i++) {
					if (this.bcc[i] != null && this.bcc[i].length() != 0) {
						count++;
					}
				}

				final InternetAddress[] bccaddress = new InternetAddress[count];

				for (int i = 0; i < count; i++) {
					if (this.bcc[i] != null && this.bcc[i].length() != 0) {
						bccaddress[i] = new InternetAddress(this.bcc[i]);
					}

				}
				msg.setRecipients(Message.RecipientType.BCC, bccaddress);

			}

			// 設定寄件人
			msg.setFrom(new InternetAddress(this.from));

			// 設定郵件主旨
			msg.setSubject(this.subject, "UTF-8");

			// 設定寄件日期
			msg.setSentDate(new Date());
			// 若無附加檔案,則加入郵件內容後寄送
			if (this.file != null && this.file.length == 0) {
				// msg.setText(msgText); //純文字

				msg.setContent(this.msgText, "text/html;charset=UTF-8"); //
				// HTML+CSS+JAVASCRIPT
				// msg.setText(this.msgText, "UTF-8");
			} else {
				// create and fill the first message part
				final MimeBodyPart[] mbp = new MimeBodyPart[this.file.length + 1];
				final FileDataSource[] fds = new FileDataSource[this.file.length];
				// create the Multipart and its parts to it
				final Multipart mp = new MimeMultipart();

				for (int i = 0; i < this.file.length + 1; i++) {
					mbp[i] = new MimeBodyPart();
					if (i == 0) {
						mbp[i].setText(this.msgText, "UTF-8");
					} else {

						// . 設定附加檔案
						// . 附加檔案暫只限於同台機器上之檔案
						// . 附加檔案必須包含完整之實際路徑之字串
						fds[i - 1] = new FileDataSource(this.file[i - 1]);
						mbp[i].setDataHandler(new DataHandler(fds[i - 1]));
						mbp[i].setFileName(MimeUtility.encodeText(fds[i - 1].getName(), "UTF-8", "B"));
					}
					mp.addBodyPart(mbp[i]);
				}
				// 將郵件內容及附加檔案加入郵件
				msg.setContent(mp);
			}

			// 寄送郵件
			Transport.send(msg);
		}

	}

	/**
	 * 設定密件副本收件人
	 * 
	 * @param String
	 *            單筆或多筆電子郵件地址字串(需以分號隔開)
	 */
	public void setBCC(final String bcc) {
		this.bcc = this.toArray(bcc);
	}

	/**
	 * 設定密件副本收件人
	 * 
	 * @param String
	 *            [] 單筆或多筆電子郵件地址陣列
	 */
	public void setBCC(final String[] bcc) {
		this.bcc = bcc;
	}

	/**
	 * 設定副本收件人
	 * 
	 * @param String
	 *            單筆或多筆電子郵件地址字串(需以分號隔開)
	 */
	public void setCC(final String cc) {
		this.cc = this.toArray(cc);
	}

	/**
	 * 設定副本收件人
	 * 
	 * @param String
	 *            [] 單筆或多筆電子郵件地址陣列
	 */
	public void setCC(final String[] cc) {
		this.cc = cc;
	}

	/**
	 * 設定附加檔案
	 * 
	 * @param String
	 *            [] 單筆或多筆檔案之完整實體路徑名稱陣列
	 */

	public void setFile(final String[] file) {
		this.file = file;
	}

	/**
	 * 將以分號分隔的多個電子郵件位址,拆解塞入陣列內
	 * 
	 * @param String
	 *            電子郵件位址字串
	 */
	private String[] toArray(final String str) {
		String[] tmpArr = null;
		String first = "yes";
		String temp = "";
		int count = 0;
		if (str.trim().length() != 0) {
			final StringTokenizer st = new StringTokenizer(str);
			while (st.hasMoreTokens()) {
				temp = st.nextToken(",");
				if (first.equals("yes")) {
					count = st.countTokens();
					tmpArr = new String[count + 1];
					first = "no";
				}
				tmpArr[count - st.countTokens()] = temp;
			}
		}
		return tmpArr;
	}

}
