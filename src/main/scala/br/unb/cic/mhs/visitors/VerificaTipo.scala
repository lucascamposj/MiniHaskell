package br.unb.cic.mhs.visitors

import br.unb.cic.mhs.ast._
import br.unb.cic.mhs.memoria.{AmbienteDecFuncao, AmbienteExpressao, AmbienteExpressaoLambda}

/**
  * Created by lucas-mac on 04/07/17.
  */
class VerificaTipo extends MHSVisitor[Tipo]{
    override def visitar(e: ValorInteiro): Tipo = TInteiro

    override def visitar(e: ValorBooleano): Tipo = TBooleano

    override def visitar(e: DecLambda): Tipo = e.tipoCorpo

    override def visitar(e: ExpressaoBinaria): Tipo = {
        if(e.lhs.aceitar(this) == e.rhs.aceitar(this)){
            e.lhs.aceitar(this)
        }else{
            TErro
        }
    }

    override def visitar(e: ExpressaoSoma): Tipo = {
        if(e.lhs.aceitar(this) == e.rhs.aceitar(this) && e.lhs.aceitar(this) == TInteiro){
            e.lhs.aceitar(this)
        }else{
            TErro
        }
    }

    override def visitar(e: ExpressaoITE): Tipo = {
        if(e.condicao.aceitar(this) == TBooleano && (e.clausulaElse.aceitar(this) == e.clausulaThen.aceitar(this))){
            e.clausulaThen.aceitar(this)
        }else{
            TErro
        }
    }

    override def visitar(e: Aplicacao): Tipo = {

        val funcao = AmbienteDecFuncao.pesquisar(e.nome)
        for(i <- funcao.args.indices){
            AmbienteExpressao.associar(funcao.args(i), e.args(i))
            if(funcao.tipoArg(i) != e.args(i).aceitar(this))
                return TErro
        }
        if(funcao.corpo.aceitar(this) != TErro)
            funcao.corpo.aceitar(this)
        else
            TErro

    }

    override def visitar(e: AplicacaoLambda): Tipo = {

        AmbienteExpressaoLambda.associar(e.expLambda.asInstanceOf[DecLambda].arg, e.valor)
        if(e.valor.aceitar(this) == e.expLambda.asInstanceOf[DecLambda].tipoCorpo &&
            e.expLambda.asInstanceOf[DecLambda].corpo.aceitar(this) == e.expLambda.asInstanceOf[DecLambda].tipoRetorno
        ) e.expLambda.aceitar(this)
        else
            TErro
    }

    override def visitar(l: ExpressaoLet): Tipo = {
        var actualExp : Expressao = null
        try{
            actualExp = AmbienteExpressao.pesquisar(l.id)
            AmbienteExpressao.associar(l.id, l.expNomeada)
        }catch{
            case e : java.util.NoSuchElementException => AmbienteExpressao.associar(l.id, l.expNomeada)
        }

        val c =  l.corpo.aceitar(this)
        AmbienteExpressao.associar(l.id, actualExp)
        c
    }

    override def visitar(e: Referencia): Tipo = {
        val exp = AmbienteExpressao.pesquisar(e.id)
        if(exp != null)
            exp.aceitar(this)
        else
            TErro
    }

    override def visitar(e: ReferenciaLambda): Tipo = {
        val exp = AmbienteExpressaoLambda.pesquisar(e.id)
        if(exp != null)
            exp.aceitar(this)
        else
            TErro
    }

    override def visitar(e: ExpressaoFatorial): Tipo = {
        if(e.v.aceitar(this) == TInteiro)
            TInteiro
        else
            TErro
    }

    override def visitar(e: ExpressaoNegativa): Tipo = {
        if(e.v.aceitar(this) == TBooleano)
            TBooleano
        else
            TErro
    }

    override def visitar(e: ExpressaoDiferenca): Tipo = {
        if(e.lhs.aceitar(this) == TInteiro && e.rhs.aceitar(this) == TInteiro)
            TBooleano
        else
            TErro
    }

    override def visitar(e: ExpressaoIgualdade): Tipo = {
        if(e.lhs.aceitar(this) == TInteiro && e.rhs.aceitar(this) == TInteiro)
            TBooleano
        else
            TErro

    }

    override def visitar(e: ExpressaoMaiorQue): Tipo = {
        if(e.lhs.aceitar(this) == TInteiro && e.rhs.aceitar(this) == TInteiro)
            TBooleano
        else
            TErro
    }

    override def visitar(e: ExpressaoMenorQue): Tipo = {
        if(e.lhs.aceitar(this) == TInteiro && e.rhs.aceitar(this) == TInteiro)
            TBooleano
        else
            TErro
    }
}
