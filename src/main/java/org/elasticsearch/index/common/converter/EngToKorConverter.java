package org.elasticsearch.index.common.converter;

import java.util.Map;

import org.elasticsearch.index.common.parser.AbstractKoreanParser;
import org.elasticsearch.index.common.util.JamoUtil;
import org.elasticsearch.index.common.util.KeyboardUtil;

public class EngToKorConverter extends AbstractKoreanParser {
      
    public String convert(String token) {        
        StringBuilder sb = new StringBuilder();

        // 문자열을 한글자씩 잘라서 처리한다.
        String word = token.trim();
       
        
        for (int index = 0; index < word.length();) {
        	
            if (index >= word.length()) {
            	break;
            }
            
         	// 처리 불가능한 글자는 그냥 넘긴다.
            if (KeyboardUtil.IGNORE_CHAR.indexOf(word.substring(index, index + 1)) > -1) {
                sb.append(word.substring(index, index + 1));
                index++;
                continue;
            }
            
            try {
            	
            	Map<String, Integer> m = KeyboardUtil.getInfoForChoSung(index, word);
            	int cho = m.get("code");
            	index = m.get("idx");
            	
            	Map<String, Integer> mJungSung = KeyboardUtil.getInfoForJungSung(index, word);
            	int jung = mJungSung.get("code");
            	index = mJungSung.get("idx");
            	
            	
                Map<String, Integer> mJongSung = KeyboardUtil.getInfoForJongSung(index, word);
                int jong = mJongSung.get("code");
                index = mJongSung.get("idx");
                
                // 한글 유니코드를 생성한다.
                sb.append((char) (JamoUtil.START_KOREA_UNICODE + cho + jung + jong));
            } catch(Exception e) {}
        }
        
        return sb.toString();
    }

	@Override
	protected void processForKoreanChar(StringBuilder sb, char chosung, char jungsung, char jongsung) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void processForOther(StringBuilder sb, char eachToken) {
		// TODO Auto-generated method stub
		
	}
    
}
