O sistema em si, usa um banco de dados H2, para guardar as informacoes em memoria e nao precisar instalar um BD.

## Parte 1
Criado o controller IntegrationController ("/api/integracao), para gerenciar e carregar os dados da API PoaTrasnporte.

- Para carregar os dados da API usar o endpoint:
```
GET {URL_SERVIDOR}/api/integracao
```

## Parte 2
Criado o controller LaneController ("/api/linha") para tratar todas as informacoes de linhas.

- Para **FILTRAR** as linhas de onibus por nome:
```
GET {URL_SERVIDOR}/api/linha/?nome=abcd
```
> Usar o parametro nome para buscar as linhas que usem o nome da linha "abcd"

- Para **LISTAR** todas as linhas cadastradas:
```
GET {URL_SERVIDOR}/api/linha/
```

## Parte 3

### Linhas

- Para **CRIAR** uma nova linha de onibus:
```
POST {URL_SERVIDOR}/api/linha
Body
{
    "idlinha":2020,
    "nome":"Lanasdasde",
    "codigo":"teste"
}
```

- Para **ATUALIZAR** uma linha de onibus
```
POST {URL_SERVIDOR}/api/linha
Body
{
    "id":1,
    "idlinha":2020,
    "nome":"Lanasdasde",
    "codigo":"teste"
}
```
> Obs.: caso o ID "1", nao exista ele ira ser criado.

- Para **PESQUISAR** uma linha por ID:
```
GET {URL_SERVIDOR}/api/linha/pesquisar?id=123
```

> Usar o parametro id para **BUSCAR** a linha que seja ID "123"


- Para **PESQUISAR** uma linha por ID Linha:
```
GET {URL_SERVIDOR}/api/linha/pesquisar?idLinha=456
```
> Usar o parametro idLinha para **BUSCAR** a linha que seja ID Linha "456"


- Para **APAGAR** uma linha por ID:
```
DELETE {URL_SERVIDOR}/api/linha?id=123
```

> Usar o parametro id para **APAGAR** a linha que seja ID "123"


### Itinerario
- Para **CRIAR** um novo itinerario:
```
POST {URL_SERVIDOR}/api/itinerario
Body
{
    "idLinha": 9191,
    "idLocalizacao": 9,
    "trajeto": 151
}
```

- Para **ATUALIZAR** um Itinerario
```
POST {URL_SERVIDOR}/api/itinerario
Body
{
    "idLinha": 9191,
    "idLocalizacao": 9,
    "trajeto": 151
}
```
> Obs.: caso o idLinha "9191" e idLocalizacao "9", nao exista ele ira ser criado.

- Para **PESQUISAR** um Itinerario por ID Linha:
```
GET {URL_SERVIDOR}/api/itinerario?idLinha=123
```

> Usar o parametro idLinha para **BUSCAR** o Itinerario que seja ID Linha "123"


- Para **LISTAR** todos os itinerarios cadastrados:
```
GET {URL_SERVIDOR}/api/itinerario
```

- Para **APAGAR** um itinerario por ID Linha e ID Localizacao:
```
DELETE {URL_SERVIDOR}/api/itinerario?idLinha=123&idLocalizacao=456
```


##Parte 4

- Para **FILTRAR** linhas por meio de um raio:
```
GET {URL_SERVIDOR}/api/linha/pesquisar?idLinha=456
```
> Usar o parametro idLinha para **BUSCAR** a linha que seja ID Linha "456"



##Parte 5
A URL [http://datapoa.com.br/storage/f/2017-03-24T13%3A38%3A33.430Z/taxis.csv][Link] fornecida, nao esta "funcionando
" e o site nao possui informacoes sobre os pontos de "Taxi"

[Link]: http://datapoa.com.br/storage/f/2017-03-24T13%3A38%3A33.430Z/taxis.csv