package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoNegativa, ValorBooleano}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoNegativaTeste extends FlatSpec with Matchers{
    "A negativa de uma expressao true" should "levar ao valor false" in {
        val negativa = new ExpressaoNegativa(new ValorBooleano(true));
        negativa.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }

    "A negativa de uma expressao false" should "levar ao valor true" in {
        val negativa = new ExpressaoNegativa(new ValorBooleano(false));
        negativa.avaliar().asInstanceOf[ValorBooleano].valor should be (true);
    }
}