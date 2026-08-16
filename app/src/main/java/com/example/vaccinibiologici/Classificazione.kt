package com.example.vaccinibiologici

//Rappresenta l'esito della valutazione di un vaccino per il paziente.
//Il livello di severità indica la gerarchia sulla quale si basa la scelta:
//La valutazione si basa su 3 criteri: Terapia, Età e patologie concomitanti.
//Se diversi criteri forniscono suggerimenti diversi sullo stesso vaccino,
//l'esito finale sarà quello con severità più alta.

enum class Classificazione(val severita: Int) {
    RACCOMANDATO(1),
    POSSIBILE(2),
    CONTROINDICATO(3)
}