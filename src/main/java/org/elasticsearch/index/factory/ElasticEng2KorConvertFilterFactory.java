package org.elasticsearch.index.factory;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.common.converter.EngToKorConverter;
import org.elasticsearch.index.filter.ElasticTokenFilter;
import org.elasticsearch.index.property.ConstProperties;


public class ElasticEng2KorConvertFilterFactory extends AbstractTokenFilterFactory {

    
    public ElasticEng2KorConvertFilterFactory(IndexSettings indexSettings, Environment env , String name, Settings settings) {
        super(indexSettings, name, settings);
    }
    

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new ElasticTokenFilter(tokenStream, new EngToKorConverter(), ConstProperties.CONVERT);
    }
}