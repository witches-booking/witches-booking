package com.witches.schedule.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultVO {
	
	private int showId;
	
	private String reMsg;
	private String reCode;
	
	public ResultVO(String reCode, int showId, String reMsg) {
        this.reCode = reCode;
        this.showId = showId;
        this.reMsg = reMsg;
    }
	
    public ResultVO(String reCode) {
        this.reCode = reCode;
        this.reMsg = getResultDescription(reCode);
    }

    private String getResultDescription(String reCode) {
        switch (reCode) {
            case "00":
                return "성공";
            case "01":
                return "실패";
            case "02":
                return "필수값 오류";
            case "03":
                return "지난 날짜";
            case "99":
                return "관리자 확인 필요";
            default:
                return "알 수 없는 결과 코드";
        }
    }
    
}
