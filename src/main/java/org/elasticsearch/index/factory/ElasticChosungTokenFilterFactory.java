package org.elasticsearch.index.factory;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.common.parser.KoreanChosungParser;
import org.elasticsearch.index.filter.ElasticTokenFilter;
import org.elasticsearch.index.property.ConstProperties;


public class ElasticChosungTokenFilterFactory extends AbstractTokenFilterFactory {
	
    public ElasticChosungTokenFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream stream) {
        return new ElasticTokenFilter(stream, new KoreanChosungParser(), ConstProperties.PARSER);
    }
    
}
