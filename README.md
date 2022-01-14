# ElasticSearch Korean Parser Plug-in

1. 소개
2. 빌드 방법
3. 사용 방법

<br>

## 소개

<p>ElasticSearch 에서 한글문장을 쉽게 검색하기 위한 플러그인입니다. 자동완성 및 검색결과를 효율적으로 사용하기 위한 플러그인으므로써 본 코드는 <code>https://github.com/javacafe-project/elasticsearch-plugin</code> 를 참고하여 작성하였습니다. <br>

Suggest API 를 통해 영문/한글 검색어 추천을 할 수 있었습니다. 그중 Term Suggest API 를 사용하면 인덱스 내의 있는 단어를 이용해 비슷한 단어가 추천됩니다. 하지만 Suggest API 는 한글의 경우 자소 단위로 분해해서 넣지 않으면 편집거리의 계산이 글자별로 적용되기 때문에 한글을 적용하기에 다소 무리가 있었기 때문에 본 플러그인을 통해 자소 단위 별로 분해하여 편집거리의 계산이 가능합니다. </p>

<br>

## 빌드 방법

엘라스틱서치의 플러그인을 설치하기 위해서는 엘라스틱서치 버전을 확인해야 합니다. 그리고 플러그인 내의 내부 파일에서 엘라스틱서치 버전을 명시해야 합니다. 다음은 버전을 확인하고 변경한 후 빌드하는 방법에 대해 설명하고 있습니다.

<br>

```
curl http://localhost:9200
```

<br>
엘라스틱 버전이 나오게 되면 엘라스틱 버전을 pom.xml에 작성하시기 바랍니다. 작성이 완료됬으면 아래와 같이 빌드하면 됩니다.

```
mvn clean compile install
```

<br>

## 사용 방법

<p> 빌드를 했다고 가정한 후 설명하겠습니다. target 폴더 내의 korean-parser-{version}.zip 형식의 파일이 들어있습니다. 아래와 같이 플러그인을 인스톨해주시기 바랍니다.</p>

```
cd /usr/share/elasticsearch/bin
./elasticsearch-plugin install file://<경로>
```

<br>

삭제 방법은 아래와 같습니다.

```
cd /usr/share/elasticsearch/bin
./elasticsearch-plugin remove korean-parser
```

<br>

## 레퍼런스

엘라스틱서치 실무 가이드 책을 지필해주신 javacafe 여러분, 다시한번 감사말씀 드립니다.

<p>https://github.com/javacafe-project/elasticsearch-plugin</p>
