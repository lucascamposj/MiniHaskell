package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast.{DecLambda, ExpressaoLet}

/**
  * Created by lucas-mac on 06/07/17.
  */
class TransformaLet extends TransformacaoG {

    private val VT = new VerificaTipo
    override def visitar(e : ExpressaoLet) : DecLambda = {
        val tipoExpNomeada = e.expNomeada.aceitar(VT)
        val tipoCorpo = e.corpo.aceitar(VT)
        val Lambda = new DecLambda(e.id, tipoExpNomeada, e.corpo, tipoCorpo)
        Lambda
    }
}