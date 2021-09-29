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

Una prima domanda da porsi riguarda la *mutabilità*, che non appare necessaria
dato il problema assegnato che consiste nel descrivere alcuni rivestimenti
essendo in grado di calcolarne il prezzo e la superficie. Per questa ragione, le
entità in gioco verranno tutte specificate come immutabili.

### Il rivestimento

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

la cui interfaccia corrisponde al seguente codice

:::{literalinclude} ../../src/piastrelle/Rivestimento.java
:language: java
:::

### Le piastrelle

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
tipo. Il diagramma precedente diventa quindi:

:::{mermaid}
:align: center

classDiagram
class Rivestimento {
  <<interface>>
  superficie()
  costo()
}
class Piastrella {
  <<abstract>>
  +costo
}
class PiastrellaQuadrata
class PiastrellaTriangolare
class PiastrellaRomboidale
class Pavimentazione
Rivestimento <|-- Piastrella
Rivestimento <|-- Pavimentazione
Piastrella <|-- PiastrellaQuadrata
Piastrella <|-- PiastrellaTriangolare
Piastrella <|-- PiastrellaRomboidale
:::

Un primo abbozzo di implementazione della piastrella è dato dal seguente codice

:::{literalinclude} ../../src/piastrelle/Piastrella.java
:language: java
:::

Occorre osservare che la classe è astratta in quanto il metodo `superficie`
prescritto dall'interfaccia che implementa non è un suo metodo concreto (ragione
per cui, tra l'altro, non c'è bisogno di aggiungerlo come metodo astratto).

Inoltre, poiché l'unico attributo della classe è il `costo` che è di tipo
primitivo, è sufficiente indicare il modificatore `final` per garantire che il
suo valore resti immutabile; sebbene potrebbe essere esposto come `public`, per
soddisfare l'interfaccia è necessario l'uso di un *getter*. Unica accortezza,
dal momento che il costo deve essere positivo (unico invariante di
rappresentazione della classe) è necessario controllarne il valore in
costruzione.

Le sottoclassi concrete sono elementari da implementare. Una bozza di codice che
comprenda solo un costruttore e quanto necessario a soddisfare l'interfaccia
(ereditata dalla classe astratta) è il seguente

:::{literalinclude} ../../src/piastrelle/PiastrellaQuadrata.java
:language: java
:::

:::{literalinclude} ../../src/piastrelle/PiastrellaRomboidale.java
:language: java
:::

:::{literalinclude} ../../src/piastrelle/PiastrellaTriangolare.java
:language: java
:::

Osserviamo che gli attributi sono rappresentabili con variabili di tipo
primitivo, l'immutabilità è quindi garantita dall'attributo `final` ed è
ragionevole omettere i *getter* rendendo l'attributo `public`.

Come sopra, l'unica accortezza è garantire l'invariante che quantomeno richiede
che ciascuna dimensione sia positiva; nel caso delle piastrelle romboidali è
comodo che il costruttore accetti due valori per le diagonali (comunque
ordinati, per facilitare l'uso del costruttore da parte degli utenti della
classe), ma è ragionevole che la rappresentazione distingua la maggiore dalla
minore: questo richiede che l'assegnamento dei parametri del costruttore agli
attributi della classe sia fatto avvedutamente.

### La pavimentazione

La pavimentazione deve immagazzinare una collezione di rivestimenti con le
relative quantità. Per ottenere questo risultato ci sono (almeno) tre
possibilità:

* due *vettori* (o *liste*) "paralleli", uno di rivestimenti e uno di interi
  (della stessa dimensione), che in ciascuna coordinata indichino
  rispettivamente un rivestimento e la relativa quantità,
* una *mappa* dai rivestimenti agli interi che ne indicano la quantità,
* un *vettore* (o *lista*) di "record" ciascuno dei quali contenga una coppia
  rivestimento e quantità.

La prima possibilità richiede una certa attenzione nel mantenimento
dell'invariante di rappresentazione (avere a che fare con due attributi che
vanno mantenuti in modo coordinato può non essere del tutto banale), la seconda
richiede l'uso delle Collections (che potrebbe non essere ovvio), mentre la
terza sempra la più semplice.

Procediamo quindi con l'implementare un record, che chiameremo `Componente`;
osserviamo che è sensato che esso implementi l'interfaccia `Rivestimento` (è
infatti in grado di calcolare la sua superficie e costo, essendogli nota quella
del rivestimento da cui è composto e dalla sua quantità).

:::{literalinclude} ../../src/piastrelle/Pavimentazione.java
:language: java
:lines: 9 - 28
:::

Anche questa entità è immutabile, il suo stato è dato da due attributi che ne
rappresentano lo stato a patto che il rivestimento sia non nullo e la quatità
positiva, invariante che è controllato in costruzione.

A questo punto lo stato della pavimentazione è semplicemente dato da una lista
di componenti, tale rappresentazione è valida a patto che:

* ai componenti non corrisponda un riferimento a `null`,
* la lista non sia vuota e
* nessun componente (contenuto nella lista) sia un riferimento a `null`.

Lo stato ed il costruttore (che garantisce tale invariante) sono dati dal codice
seguente:

:::{literalinclude} ../../src/piastrelle/Pavimentazione.java
:language: java
:lines: 31 - 39
:emphasize-lines: 8
:::

Si noti la linea evidenziata in cui all'attributo della classe non è assegnato
il valore del riferimento passato come parametro (che restando in possesso del
chiamante renderebbe la esposta la rappresentazione), ma viene fatta una copia
tramite il costruttore di `ArrayList` e quindi viene resa immutabile avvolgendo
la copia con il metodo statico `Collections.unmodifiableList` che ne restituisce
una versione non modificabile.
