package org.elasticsearch.index.factory;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.common.parser.KoreanJamoParser;
import org.elasticsearch.index.filter.ElasticTokenFilter;
import org.elasticsearch.index.property.ConstProperties;


public class ElasticJamoTokenFilterFactory extends AbstractTokenFilterFactory {

	public ElasticJamoTokenFilterFactory(IndexSettings indexSettings, String name, Settings settings) {
		super(indexSettings, name, settings);
		// TODO Auto-generated constructor stub
	}
	
    public ElasticJamoTokenFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);
    }
    

	@Override
	public TokenStream create(TokenStream tokenStream) {
		return new ElasticTokenFilter(tokenStream, new KoreanJamoParser(), ConstProperties.PARSER);
	}
}
