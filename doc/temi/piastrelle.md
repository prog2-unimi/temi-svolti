# Piastrelle

## Le entità

La traccia inizia con l'elencazione di alcune delle entità utili a gestire
alcuni aspetti dell'attività di un piastrellista.
### La piastrella

Una **piastrella** è un elemento architettonico usato per rivestire la
superficie dei pavimenti; ne esistono di diversi tipi, ciascuno dei quali è
caratterizzato da una serie di caratteristiche, tra le quali almeno la
*superficie* e il *costo* unitario (che per semplicità assumeremo rappresentati
da numeri interi).

Esempi di piastrelle possono essere: triangolari (caratterizzate da base e
altezza), quadrate (caratterizzate dalla lunghezza del lato), romboidali
(caratterizzate dalla lunghezza delle due diagonali) e così via…

### La pavimentazione

Una **pavimentazione** è costituita da una collezione (finita e non vuota) di
piastrelle, o altre pavimentazioni; la sua *superficie* è pari alla somma delle
superfici di tutte le piastrelle che contiene (direttamente o indirettamente) e
il suo *costo* totale è dato dalla somma dei costi di tutte le piastrelle che
contiene. Evidentemente una pavimentazione non può contenere sé stessa
(direttamente o indirettamente).

Se ad esempio:

* la pavimentazione della cucina è fatta di 42 piastrelle quadrate di lato 2 e
  prezzo 3,
* quella del bagno è fatto di 65 piastrelle romboidali con diagonali di
  lunghezza 4 e 2 con prezzo 5 e
* la pavimentazione della casa è costituita da 1 pavimentazione della cucina più
  2 pavimentazioni del bagno,

la superficie della pavimentazione della casa è pari a

    42 x (2 x 2) + 2 x [65 x (4 x 2) / 2] = 688

dove il primo addendo corrisponde alla superficie della pavimentazione della
cucina, mentre la quantità tra parentesi quadre corrisponde alla superficie
della pavimentazione del bagno. Similmente, il costo della pavimentazione della
casa è dato da

    42 x 3 + 2 x [65 x 5] = 776

dove i due addendi sono rispettivamente i costi della pavimentazione della
cucina e dei due bagni.

## Una prima bozza di specificazione

La prima cosa che salta all'occhio è che *superficie* e *costo* sono proprietà
di entrambe le entità; questo suggerisce che potrebbero essere convenientemente
raccolte in una *interfaccia* (che potremmo chiamare `Rivestimento`) questo
consentirebbe, tra l'altro, di trattare le due entità in modo omogeneo grazie al
*polimorfismo* (fatto che sembra particolarmente utile dal momento che una
pavimentazione può essere costituita da entrambe le entità). La situazione al
momento è quindi rappresentata dal seguente diagramma

:::{mermaid}
:align: center

classDiagram
class Rivestimento {
    <<interface>>
    superficie()
    costo()
}
class Piastrella
class Pavimentazione
Rivestimento <|-- Piastrella
Rivestimento <|-- Pavimentazione
:::

Per quanto concerne le piastrelle, ne sono presenti di diverso tipo, distinte
quanto meno dalla forma (ma in linea di principio potrebbero essere costituite
di materiali diversi, o avere proprietà diverse, ad esempio dal punto di vista
termico, o di resistenza); questo sembra suggerire lo sviluppo di una gerarchia
di tipi, sopratutto in considerazione del fatto che quanto meno è difficile
immaginare la segnatura di un costruttore comune alle diverse forme. Una delle
competenze che distingue le piastrelle è certamente quella di calcolare la
propria superficie a partire dalle misure che la caratterizzano, viceversa, il
prezzo appare come una caratteristica comune a tutte le sottoclassi. Queste
considerazioni fanno propendere verso la realizzazione di una classe astratta il
cui stato coincida col solo prezzo, da cui derivare le sottoclassi di diverso
tipo.