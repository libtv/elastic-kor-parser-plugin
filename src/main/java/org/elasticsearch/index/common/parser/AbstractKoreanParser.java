package org.elasticsearch.index.common.parser;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.common.util.JamoUtil;

public abstract class AbstractKoreanParser {

    public String parse(String token) {
    	
        if (StringUtils.isBlank(token)) {
            return "";
        }

        StringBuilder rb = new StringBuilder();

        for(char ch : token.toCharArray()) {
            
            // 처리 할 char의 유니코드 인덱스를 구한다.
            char koreanUnicodeIdx = (char)(ch - JamoUtil.START_KOREA_UNICODE);

            if(koreanUnicodeIdx >= 0 && koreanUnicodeIdx < 11185) {
                // 초성 유니코드
                int idxChoSung = koreanUnicodeIdx / JamoUtil.CHOSUNG_ITERATOR_INTERVAL;
                char chosung = JamoUtil.UNICODE_CHO_SUNG[idxChoSung];
                                
                // 중성 유니코드
                int idxJungSung = koreanUnicodeIdx % JamoUtil.CHOSUNG_ITERATOR_INTERVAL / JamoUtil.JUNGSUNG_ITERATOR_INTERVAL;
                char jungsung = JamoUtil.UNICODE_JUNG_SUNG[idxJungSung];
                
                // 종성 유니코드
                int idxJongSung = koreanUnicodeIdx % JamoUtil.JUNGSUNG_ITERATOR_INTERVAL;
                char jongsung = JamoUtil.UNICODE_JONG_SUNG[idxJongSung];

                // 한글 한글자를 처리한다.
                processForKoreanChar(rb, chosung, jungsung, jongsung);
            
            } else {
                
                // 한글이 아닌 한글자를 처리한다.
                processForOther(rb, ch);
            }
        }

        // 토큰을 분석한 최종 결과를 리턴한다.
        return rb.toString();
    }
    

    /**
     * 한글 문자를 처리한다.
     * 
     * @param sb
     * @param chosung
     * @param jungsung
     * @param jongsung
     */
    protected abstract void processForKoreanChar(StringBuilder sb, char chosung, char jungsung, char jongsung);
    
    
    /**
     * 한글 문자를 제외한 일반 문자를 처리한다.
     * 
     * @param sb
     * @param eachToken
     */
    protected abstract void processForOther(StringBuilder sb, char eachToken);

    public abstract String convert(String token);
    
}
