package org.elasticsearch.plugin.analysis;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.index.factory.ElasticChosungTokenFilterFactory;
import org.elasticsearch.index.factory.ElasticEng2KorConvertFilterFactory;
import org.elasticsearch.index.factory.ElasticJamoTokenFilterFactory;
import org.elasticsearch.index.factory.ElasticKor2EngConvertFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

public class KoreanParserPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {        
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> map = new HashMap<>();
        
        // (1) 한글 자모 분석 필터
        map.put("elastic_jamo", ElasticJamoTokenFilterFactory::new);
        
        // (2) 한글 초성 분석 필터 
        map.put("elastic_chosung", ElasticChosungTokenFilterFactory::new);
        
        // (3) 영한 오타 변환 필터
        map.put("elastic_eng2kor", ElasticEng2KorConvertFilterFactory::new);
        
        // (4) 한영 오타 변환 필터
        map.put("elastic_kor2eng", ElasticKor2EngConvertFilterFactory::new);
                        
        return map;
    }
}
