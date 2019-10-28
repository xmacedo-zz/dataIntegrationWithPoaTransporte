package br.com.xmacedo.configuration;

public class Constants {
    public static final String PATHS = "/api/v1.*";
    public static final String BASE_URL = "http://www.poatransporte.com.br/php/facades/process.php";
    public static final String END_LIST_LANES = "?a=nc&p=%&t=o";

    public static final String END_LIST_ITINERARY_BY_LANE = "?a=il&p=";
    public static final String ENDPOINT_FIND_LINHA_POR_NOME = "?a=nc&t=o&p=";
    public static final String ENDPOINT_FIND_LINHA_POR_CODIGO = "?a=nc&t=o&p=";
    public static final String ENDPOINT_LIST_LINHAS_ROTA = "?a=tp&p=";
}
