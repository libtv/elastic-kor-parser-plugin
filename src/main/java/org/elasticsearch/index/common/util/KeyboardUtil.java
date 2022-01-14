package org.elasticsearch.index.common.util;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.common.type.CodeType;

/**
 * 한글 키보드 유틸리티
 * 
 *
 * @author hrkim
 *
 */
public class KeyboardUtil {

    
    /**
     * Converter 진행시 무시되는 문자들
     */
    public static final String IGNORE_CHAR = "`1234567890-=[]\\;',./~!@#$%^&*()_+{}|:\"<>?\' \' ";

    
    
    /**
     * 초성 키에 해당하는 키보드상의 영문자 (19자)
     */
    public static final String[] KEYBOARD_CHO_SUNG = {
            "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", 
            "T", "d", "w", "W", "c", "z", "x", "v", "g"
    };
    
    /**
     * 중성 키에 해당하는 키보스상의 영문자 (21자)
     */
    public static final String[] KEYBOARD_JUNG_SUNG = {
            "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", 
            "ho", "hl", "y", "n", "nj", "np", "nl", "b", "m", "ml", "l"
    };
    
    /**
     * 종성 키에 해당하는 키보드상의 영문자 (27자) - "빈값" 제외
     */
    public static final String[] KEYBOARD_JONG_SUNG = {
            "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", 
            "fq", "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", 
            "d", "w", "c", "z", "x", "v", "g"
    };
        


    /**
     * 키보드상에서 한영키에 의해서 오타 교정이 필요한 키배열 (영문키 33자)
     */
    public static final String[] KEYBOARD_KEY_ENG = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
            "u", "v", "w", "x", "y", "z", "Q", "W", "E", "R", 
            "T", "O", "P"
    };
    
    /**
     * 키보드상에서 한영키에 의해서 오타 교정이 필요한 키배열 (한글키 33자)
     */
    public static final String[] KEYBOARD_KEY_KOR  = {
            "ㅁ", "ㅠ", "ㅊ", "ㅇ", "ㄷ", "ㄹ", "ㅎ", "ㅗ", "ㅑ", "ㅓ", 
            "ㅏ", "ㅣ", "ㅡ", "ㅜ", "ㅐ", "ㅔ", "ㅂ", "ㄱ", "ㄴ", "ㅅ", 
            "ㅕ", "ㅍ", "ㅈ", "ㅌ", "ㅛ", "ㅋ", "ㅃ", "ㅉ", "ㄸ", "ㄲ", 
            "ㅆ", "ㅒ", "ㅖ"
    };
   
    public static Map<String, Integer> getInfoForChoSung(int index, String word) {
        Map<String, Integer> m = new HashMap<>();

        int idx = index + 1;
    	int code = wordCheck(CodeType.CHOSUNG, word, index, idx);
    	if (code == -1) {
    		idx = idx - 1;
    		code = 0;
    	}
    	
        m.put("code", code);
        m.put("idx", idx);
        return m;
    }

    public static Map<String, Integer> getInfoForJungSung(int index, String word) {
    	int idx = index + 2;
    	int code = wordCheck(CodeType.JUNGSUNG, word, index, idx);
        
        if (code == -1) {
        	idx = idx - 1;
        	code = wordCheck(CodeType.JUNGSUNG, word, index, idx);
        	if (code == -1) {
        		idx = idx - 1;
        		code = 0;
        	}
        }
        
        Map<String, Integer> m = new HashMap<>();
        m.put("code", code);
        m.put("idx", idx);

        return m;
    }

    public static Map<String, Integer> getInfoForJongSung(int index, String word) {
        int code = 0;
        int idx = index + 2;
        
        int temp = wordCheck(CodeType.JONGSUNG, word, index, idx); 
        
        // 종성이 2글자 일 경우
        if (temp != -1) {
        	
        	// 종성이 2글자 와 초성이 존재 할 경우
        	int temps =  wordCheck(CodeType.JONGSUNG, word, idx, idx + 1);
        	if (temps != -1) {
        		code = temp;
        	} else {
        		// 종성이 1글자와 초성이 존재 하는 경우
        		idx = idx - 1;
        		code = wordCheck(CodeType.JONGSUNG, word, index, idx);
        		if (code == -1) {
        			idx = idx - 1;
        			code = 0;
        		}
        	}
        } else {
        	// 종성이 뒷글자를 검사
        	temp = wordCheck(CodeType.JONGSUNG, word, index + 1, idx);
        	if (temp != -1) {
        		idx = idx - 1;
        		code = wordCheck(CodeType.JONGSUNG, word, index, idx);
        		if (code == -1) {
        			code = 0;
        		}
        	} else {
        		temp = wordCheck(CodeType.JUNGSUNG, word, index + 1, idx);
        		if (temp == -1) {
        			idx = idx - 1;
        			code = wordCheck(CodeType.JONGSUNG, word, index, idx);
        			if (code == -1) {
        				code = 0;
                		idx = idx - 1;
        			}
        		} else {
        			code = 0;
            		idx = idx - 2;
        		}
        		
        	}
        }
        
        Map<String, Integer> m = new HashMap<>();
        m.put("code", code);
        m.put("idx", idx);

        return m;
    }
    
    private static int getMedial(CodeType type, String word) {
    	return makeUnicodeIndex(type, word);
    }
    
    private static int wordCheck(CodeType codeType, String word, Integer index, Integer idx) {
    	if (idx > word.length()) {
    		return - 1;
    	} else {
    		return getMedial(codeType, word.substring(index, idx));
    	}
    }

    
    /**
     * 키보드상에 매칭된 유니코드값 Index를 리턴한다.
     * 
     * @param type
     * @param sub_str
     * @return
     */
    private static int makeUnicodeIndex(CodeType type, String subStr) {
        switch (type) {
            case CHOSUNG:                
                for (int i=0; i<KEYBOARD_CHO_SUNG.length; i++) {
                    if (KEYBOARD_CHO_SUNG[i].equals(subStr)) {
                        return i * 28 * 21; 
                    }
                }                
                break;

            case JUNGSUNG:
                for (int i=0; i<KEYBOARD_JUNG_SUNG.length; i++) {
                    if (KEYBOARD_JUNG_SUNG[i].equals(subStr)) {
                        return i * 28;
                    }
                }
                break;

            case JONGSUNG:
                for (int i=0; i<KEYBOARD_JONG_SUNG.length; i++) {
                    if (KEYBOARD_JONG_SUNG[i].equals(subStr)) {
                        return i + 1;
                    }
                }
                break;
                
            default:
                break;
        }

        return -1;
    } 
}
