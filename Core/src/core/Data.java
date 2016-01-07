package core;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Representa uma data do calendário Gregoriano.
 *
 * Com esta classe e possível criar e manipular datas, podendo realizar as mais
 * importantes operações que envolvem a contagem de tempo em dias.
 *
 * A classe não opera com horas ou frações de dias.
 *
 * @version 13/03/2015
 * @author Mouriac Diemer
 *
 * @Maintenence Joao Francisco Padilha Souza
 */
public class Data
{

    //CONSTANTES
    /**
     * Formato de data com dois dígitos para o dia e mes e quatro dígitos para o
     * ano.
     */
    final static public int DDMMAAAA = 1;
    final static public int AAAAMMDD = 2;
    final static public int DIASEMANA_DDMMAAAA = 3;

    //ATRIBUTOS
    private int dia;
    private int mes;
    private int ano;

    //MÉTODOS CONSTRUTORES
    public Data(int dia, int mes, int ano)
    {
        definirComoHoje();
        definirData(dia, mes, ano);
    }

    public Data()
    {
        definirComoHoje();
    }

    public Data(String data)
    {
        definirComoHoje();
        String[] p = data.split("-");
        String[] b = data.split("/");
        System.out.println(p[0]);
        if ( p.length == 3 )
        {
            int d = Integer.parseInt(p[2]);
            int m = Integer.parseInt(p[1]);
            int a = Integer.parseInt(p[0]);
            definirData(d, m, a);
        }
        else
        {
            if ( b.length == 3 )
            {
                int d = Integer.parseInt(b[2]);
                int m = Integer.parseInt(b[1]);
                int a = Integer.parseInt(b[0]);

                definirData(d, m, a);
            }
        }
    }

    //MÉTODOS ESTÁTICOS
    public static Data criarData(int dia, int mes, int ano)
    {
        Data dt = null;
        if ( Data.validarData(dia, mes, ano) )
        {
            dt = new Data(dia, mes, ano);
        }
        return dt;
    }

    public static Data criarData(String data)
    {
        Data dt = null;
        String[] p = data.split("/");
        if ( p.length == 3 )
        {
            int d = Integer.parseInt(p[0]);
            int m = Integer.parseInt(p[1]);
            int a = Integer.parseInt(p[2]);
            if ( Data.validarData(d, m, a) )
            {
                dt = new Data(d, m, a);
            }
        }
        return dt;
    }

    public static Data criarData()
    {
        return new Data();
    }

    /**
     * Verifica se os valores informados para dia, mes e ano correspondem a uma
     * data válida.
     *
     * O valor para mes deve estar entre 1 e 12. O valor para dia deve ser maior
     * do 1 e menor do que o maior dia válido para aquele mes. O ano pode ser
     * qualquer valor positivo.
     *
     * @param dia valor do dia a ser testado
     * @param mes valor do mes a ser testado
     * @param ano valor do ano a ser testado
     * @return verdadeiro se os valores correspondem a uma data válida.
     */
    public static boolean validarData(int dia, int mes, int ano)
    {
        boolean ok = false;
//        int[] dias = new int[12];
//        dias[0] = 31;
//        dias[1] = 28;
//        dias[2] = 30;

        int[] dias =
        {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        if ( ano > 0 )
        {
            if ( Data.validarBissexto(ano) )
            {
                dias[1] = 29;
            }
            if ( mes >= 1 && mes <= 12 )
            {
                if ( dia >= 1 && dia <= dias[mes - 1] )
                {
                    ok = true;
                }
            }
        }
        return ( ok );
    }

    /**
     * Verifica se o valor informado para o ano corresponde a um ano
     * validarBissexto
     *
     * Um ano será validarBissexto quando for divisível por 400 ou quando for
     * divisível por 4 e não for divisível por 100. A cada 4 anos temos um ano
     * validarBissexto, mas a cada 100 anos há uma exceção, ou seja, os anos
     * divisíveis por 100 não são bissextos. Todavia a cada 400 anos temos outra
     * exceção, ou seja, os anos divisívies por 400 são bissextos.
     *
     * @param ano valor do ano a ser testado
     * @return verdadeiro quando o ano e validarBissexto
     */
    public static boolean validarBissexto(int ano)
    {
        return ( ano % 400 == 0 || ( ano % 4 == 0 && ano % 100 != 0 ) );
    }

    private static int obterUltimoDiaDoMes(int mes)
    {
        int[] dias =
        {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        return ( dias[mes - 1] );
    }

    //DEMAIS MÉTODOS
    public boolean bissexto()
    {
        return ( Data.validarBissexto(this.ano) );
    }

    public void incrementarUmDia()
    {
        this.dia++;
        if ( !Data.validarData(this.dia, this.mes, this.ano) )
        {
            // significa que ultrapassou o último dia do mes
            this.dia = 1;
            this.mes++;
            if ( !Data.validarData(this.dia, this.mes, this.ano) )
            {
                // significa que ultrapassou o último mes do ano
                this.mes = 1;
                this.ano++;
            }
        }
    }

    public void incrementarDias(int dias)
    {
        for ( int i = 0; i < dias; i++ )
        {
            incrementarUmDia();
        }
    }

    public void decrementarUmDia()
    {
        this.dia--;
        if ( this.dia <= 0 ) // significa que e preciso tambem voltar o mes
        {
            this.mes--;
            if ( this.mes <= 0 ) // significa que e preciso voltar o ano
            {
                this.dia = 31;
                this.mes = 12;
                this.ano--;
            }
            else // seleciona o último dia do mes
            {
                this.dia = Data.obterUltimoDiaDoMes(this.mes);
                if ( this.bissexto() && this.mes == 2 )
                {
                    this.dia++;
                }
            }
        }
    }

    public void decrementarDias(int dias)
    {
        for ( int i = 0; i < dias; i++ )
        {
            decrementarUmDia();
        }
    }

    public boolean compararDatas(Data data)
    {
        return ( this.dia == data.dia && this.mes == data.mes && this.ano == data.ano );
    }

    public Data criarDataIncrementada(int dias)
    {
        Data dtaux = new Data(dia, mes, ano);
        dtaux.incrementarDias(dias);
        return dtaux;
    }

    public int compararCom(Data data)
    {
        int r = 0;
        if ( this.obterDiaDoSeculo() < data.obterDiaDoSeculo() )
        {
            r = -1;
        }
        else if ( this.obterDiaDoSeculo() > data.obterDiaDoSeculo() )
        {
            r = 1;
        }
        return r;
    }

    //SETS E GETS
    private void definirData(int dia, int mes, int ano)
    {
        if ( Data.validarData(dia, mes, ano) )
        {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        }
    }

    public void definirDia(int dia)
    {
        if ( Data.validarData(dia, this.mes, this.ano) )
        {
            this.dia = dia;
        }
    }

    public void definirMes(int mes)
    {
        if ( Data.validarData(this.dia, mes, this.ano) )
        {
            this.mes = mes;
        }
    }

    public void definirAno(int ano)
    {
        if ( Data.validarData(this.dia, this.mes, ano) )
        {
            this.ano = ano;
        }
    }

    public int obterDia()
    {
        return ( dia );
    }

    public int obterMes()
    {
        return ( mes );
    }

    public int obterAno()
    {
        return ( ano );
    }

    public String obterExtensoDoMes()
    {
        String[] meses =
        {
            "Janeiro", "Fevereiro", "Março", "Abril",
            "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"
        };
        return ( meses[this.mes - 1] );
    }

    public String obterData() // retorna a data no formato dd/mm/aaaa
    {
        DecimalFormat df2 = new DecimalFormat("00");
        DecimalFormat df4 = new DecimalFormat("0000");
        return ( df2.format(dia) + "/" + df2.format(mes) + "/" + df4.format(ano) );
    }

    public String obterDataFormatada(int formato)
    {
        DecimalFormat df2 = new DecimalFormat("00");
        DecimalFormat df4 = new DecimalFormat("0000");
        String retorno = null;
        if ( formato == 1 )
        {
            retorno = df2.format(dia) + "/" + df2.format(mes) + "/" + df4.format(ano);
        }
        else if ( formato == 2 )
        {
            retorno = df4.format(ano) + "/" + df2.format(mes) + "/" + df2.format(dia);
        }
        else if ( formato == 3 )
        {
            retorno = this.obterDiaDaSemana() + ", " + df2.format(dia) + " de " + this.obterExtensoDoMes() + " de " + this.ano;
        }
        return retorno;
    }

    public String obterExtensoData() // retorna como no exemplo: "12 de setembro de 2006"
    {
        return ( this.dia + " de " + obterExtensoDoMes() + " de " + this.ano );
    }

    private void definirComoHoje()
    {
        // Obter a data e hora do sistema
        Calendar data = Calendar.getInstance();
        this.dia = data.get(Calendar.DAY_OF_MONTH);
        this.mes = data.get(Calendar.MONTH) + 1;
        this.ano = data.get(Calendar.YEAR);
    }

    public int obterDiferenca(Data data)
    {
        return Math.abs(this.obterDiaDoSeculo() - data.obterDiaDoSeculo());
    }

    private int obterDiaDoSeculo()
    {
        //www.inf.ufrgs.br/~cabral/Dia_do_Seculo.html
        int diaDoSeculo = ( ano - 1901 ) * 365 + ( ano - 1901 ) / 4 + dia + ( mes - 1 )
                * 31 - ( ( mes * 4 + 23 ) / 10 )
                * ( ( mes + 12 ) / 15 ) + ( ( 4 - ano % 4 ) / 4 )
                * ( ( mes + 12 ) / 15 );
        return diaDoSeculo;
    }

    public int obterDiaDaSemanaOrdinal()
    {
        return ( this.obterDiaDoSeculo() % 7 ) + 1;
    }

    public String obterDiaDaSemana()
    {
        String[] diasDaSemana =
        {
            "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira",
            "Sexta-feira", "Sábado", "Domingo"
        };
        int i = obterDiaDoSeculo() % 7;
        return diasDaSemana[i];
    }

    public int obterDiaNoAno()
    {
        int dias = 0;
        for ( int i = 1; i < mes; i++ )
        {
            dias += Data.obterUltimoDiaDoMes(i);
        }
        if ( this.bissexto() && mes > 2 )
        {
            dias += 1;
        }
        dias += dia;
        return dias;
    }

    public String toString()
    {
        return this.obterData();
    }

    public int obterUltimoDiaDoMes()
    {
        int u = Data.obterUltimoDiaDoMes(mes);
        if ( this.bissexto() && mes == 2 )
        {
            u++;
        }
        return u;
    }

    public String obterDiaPorExtenso()
    {
        int u, d;

        String extenso = "", conexao;
        String[] unidade, dezena, dezenaespecial;

        unidade = new String[10];
        dezena = new String[10];
        dezenaespecial = new String[10];

        unidade[0] = "";
        unidade[1] = "um";
        unidade[2] = "dois";
        unidade[3] = "três";
        unidade[4] = "quatro";
        unidade[5] = "cinco";
        unidade[6] = "seis";
        unidade[7] = "sete";
        unidade[8] = "oito";
        unidade[9] = "nove";

        dezena[0] = "";
        dezena[1] = "dez";
        dezena[2] = "vinte";
        dezena[3] = "trinta";
        dezena[4] = "quarenta";
        dezena[5] = "cinquenta";
        dezena[6] = "sessenta";
        dezena[7] = "setenta";
        dezena[8] = "oitenta";
        dezena[9] = "noventa";

        dezenaespecial[0] = "dez";
        dezenaespecial[1] = "onze";
        dezenaespecial[2] = "doze";
        dezenaespecial[3] = "treze";
        dezenaespecial[4] = "quatorze";
        dezenaespecial[5] = "quinze";
        dezenaespecial[6] = "dezesseis";
        dezenaespecial[7] = "dezessete";
        dezenaespecial[8] = "dezoito";
        dezenaespecial[9] = "dezenove";

        d = dia / 10;
        u = dia % 10;

        conexao = "";
        if ( d > 0 && u > 0 )
        {
            conexao = " e ";
        }

        if ( dia >= 10 && dia <= 19 )
        {
            extenso = dezenaespecial[u];
        }
        else
        {
            extenso = dezena[d] + conexao + unidade[u];
        }

        return ( extenso );
    }

    //Verão: 21 dezembro ate 20 março
    //Outono: 21 março ate 20 junho
    //Inverno: 21 junho ate 20 setembro
    //Primavera: 21 setembro ate 20 dezembro
    public String obterEstacao()
    {
        String estação = "Verão";
        if ( this.compararCom(new Data(20, 3, ano)) > 0 )
        {
            estação = "Outono";
        }
        if ( this.compararCom(new Data(20, 6, ano)) > 0 )
        {
            estação = "Inverno";
        }
        if ( this.compararCom(new Data(20, 9, ano)) > 0 )
        {
            estação = "Primavera";
        }
        if ( this.compararCom(new Data(20, 12, ano)) > 0 )
        {
            estação = "Verão";
        }
        return estação;
    }

    public boolean eFinalDeSemana()
    {
        boolean ok = false;
        String finalDeSemana = "Sábado;Domingo";
        if ( finalDeSemana.indexOf(obterDiaDaSemana()) > 0 )
        {
            ok = true;
        }
        return ok;
    }

    public boolean eNatal()
    {
        return this.compararDatas(new Data(25, 12, ano));
    }

    public int obterDiferencaDeHoje()
    {
        return this.obterDiferenca(new Data());
    }

}
