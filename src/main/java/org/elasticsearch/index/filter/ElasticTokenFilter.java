package org.elasticsearch.index.filter;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.index.common.parser.AbstractKoreanParser;
import org.elasticsearch.index.property.ConstProperties;

public final class ElasticTokenFilter extends TokenFilter {
    
    private AbstractKoreanParser parser;
    private CharTermAttribute termAtt;
    private String method;

    
    public ElasticTokenFilter(TokenStream stream, AbstractKoreanParser parser, String method) {
        super(stream);
        this.parser = parser;
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.method = method;
    }

    
    /**
     * 한글 자모 Parser를 이용하여 토큰을 파싱하고 Term을 구한다. 
     */
    @Override
    public boolean incrementToken() throws IOException {
        
        if (input.incrementToken()) {
        	CharSequence parserdData = null;
        	if (method.equals(ConstProperties.PARSER)) {
        		parserdData = parser.parse(termAtt.toString());
        		
        	} else if (method.equals(ConstProperties.CONVERT)) {
        		parserdData = parser.convert(termAtt.toString());
        	}
            
            termAtt.setEmpty();
            termAtt.append(parserdData);
        
            return true;
        }
        
        return false;
    }
    
    
}