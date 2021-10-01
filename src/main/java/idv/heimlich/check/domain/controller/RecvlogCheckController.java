package idv.heimlich.check.domain.controller;

import idv.heimlich.check.domain.service.CheckService;
import idv.heimlich.check.domain.service.impl.RecvlogCheckServiceImpl;

/**
 * 資料檢核
 */
public class RecvlogCheckController {

	private final CheckService service = new RecvlogCheckServiceImpl();

	public static void main(String[] args) {
		final RecvlogCheckController controller = new RecvlogCheckController();
		controller.doSendMail();
	}

	public void doSendMail() {
		this.service.sendMail();
	}

}
