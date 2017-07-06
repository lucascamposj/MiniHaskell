package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoDiferenca, ValorBooleano, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */

class ExpressaoDiferencaTeste extends FlatSpec with Matchers{
    "Uma diferenca entre os valores 3 e 4" should "levar ao valor true" in {
        val dif = new ExpressaoDiferenca(new ValorInteiro(3), new ValorInteiro(4));
        dif.avaliar().asInstanceOf[ValorBooleano].valor should be (true);
    }

    "Uma diferenca entre os valores 3 e 4" should "levar ao valor false" in {
        val dif = new ExpressaoDiferenca(new ValorInteiro(4), new ValorInteiro(4));
        dif.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }
}
