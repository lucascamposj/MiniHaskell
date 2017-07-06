package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoFatorial, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoFatorialTeste extends FlatSpec with Matchers {
    "Um fatorial de 6" should "levar ao valor 720" in {
        val fat = new ExpressaoFatorial(new ValorInteiro(6));
        fat.avaliar().asInstanceOf[ValorInteiro].valor should be (720);
    }
}