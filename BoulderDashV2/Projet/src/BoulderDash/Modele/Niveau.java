package BoulderDash.Modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import BoulderDash.Modele.Animation.TableAnimation;
import BoulderDash.Modele.Cases.Boue;
import BoulderDash.Modele.Cases.Case;
import BoulderDash.Modele.Cases.Chutable;
import BoulderDash.Modele.Cases.Diamant;
import BoulderDash.Modele.Cases.MurIndestructible;
import BoulderDash.Modele.Cases.MurMagique;
import BoulderDash.Modele.Cases.MurNormal;
import BoulderDash.Modele.Cases.Papillon;
import BoulderDash.Modele.Cases.Personnage;
import BoulderDash.Modele.Cases.Rocher;
import BoulderDash.Modele.Cases.Sortie;
import BoulderDash.Modele.Cases.Vide;

/**
 * level's class, describes a whole level
 * 
 *
 * 
 */
public class Niveau {
	// Variables
	private int hauteur;
	private int longueur;
	private int dscore;
	private int score;
	private float tmax;
	private boolean fini;
	private Personnage perso;
	private Sortie sortie;

	/**
	 * 
	 * table of the level's cases
	 */
	private Case[][] tableau;

	/**
	 * update's table, contains the cases that could be edited in the next cycle
	 * 
	 */
	private List<Case> UpTable;

	/**
	 * creates a level of 60*40 with the player in the upper left and the exit
	 * to the next level in bottom right
	 * 
	 */
	public Niveau() {
		this(60, 40);
	}

	/**
	 * creates a new level from a saved level
	 */
	public Niveau(String path) {
		UpTable = new ArrayList<>();
		fini = false;
		score = 0;
		importer(path);
	}

	/**
	 * creates a level l*h filled with boue and with a border of
	 * MurIndestructible
	 * 
	 * 
	 * @param l
	 *            lenght of the level
	 * @param h
	 *            height of the level
	 * @see Boue
	 * @see MurIndestructible
	 * 
	 */
	public Niveau(int l, int h) {
		int x, y;

		tableau = new Case[l][h];
		UpTable = new ArrayList<>();
		hauteur = h;
		longueur = l;
		fini = false;
		dscore = 1;

		// filling levels
		for (y = 0; y < h; y++) {
			if (y == 0 || y == (h - 1)) {
				for (x = 0; x < l; x++) {
					insereMurIndestructible(x, y);
				}
			} else {
				insereMurIndestructible(0, y);
				for (x = 1; x < (l - 1); x++) {
					insereBoue(x, y);
				}
				insereMurIndestructible(l - 1, y);
			}
		}
	}

	/**
	 * insère le personnage dans le niveau à la position indiquée en argument
	 * ; si un personnage est déja present alors il est échangé avec sa
	 * nouvelle position
	 * 
	 * 
	 * inserts the player in the level at the indicated position if a player is
	 * already in, he is transported to his new position
	 * 
	 * @param x
	 *            position en x du personage
	 * @param y
	 *            position en y du personage
	 */
	public void inserePersonage(int x, int y) {
		if (x > 0 && x < longueur - 1 && y > 0 && y < hauteur - 1) {
			if (perso == null) {
				perso = new Personnage(x, y);
				tableau[x][y] = perso;
			} else {
				echangeCases(perso.getX(), perso.getY(), x, y);
			}

		}
	}

	/**
	 * 
	 * inserts the level's exit
	 * 
	 * @param x
	 *            position en x de la sortie
	 * @param y
	 *            position en y de la sortie
	 */
	public void insereSortie(int x, int y) {
		if (x > 0 && x < longueur - 1 && y > 0 && y < hauteur - 1) {
			if (sortie == null) {
				sortie = new Sortie(x, y);
				if (perso != null && tableau[x][y] == perso) {
					perso = null;
				}
				tableau[x][y] = sortie;
			} else {
				echangeCases(sortie.getX(), sortie.getY(), x, y);
			}
		}
	}

	/**
	 * inserts a MurNormal at the indicated position
	 * 
	 * @param x
	 *            position en x de la sortie
	 * @param y
	 *            position en y de la sortie
	 */
	public void insereMurNormal(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new MurNormal(x, y);
		}
	}

	/**
	 * inserts Vide at the indicated position
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void insereVide(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new Vide(x, y);
		}
	}

	/**
	 * Inserts Boue at the indicated position
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void insereBoue(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new Boue(x, y);
		}
	}

	/**
	 * inserts MurIndestructible at the indicated position
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void insereMurIndestructible(int x, int y) {
		if (x >= 0
				&& x < longueur
				&& y >= 0
				&& y < hauteur
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new MurIndestructible(x, y);
		}
	}

	/**
	 * inserts a Rocheer at the indicated position, and adds it to the UpTable
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void insereRocher(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new Rocher(x, y);
			UpTable.add(tableau[x][y]);
		}
	}

	/**
	 * inserts a Diamant at the indicated position and adds it to the UpTable
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void insereDiamant(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new Diamant(x, y);
			UpTable.add(tableau[x][y]);
		}
	}

	/**
	 * Inserts a MurMagique at the indicated position
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void insereMurMagique(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new MurMagique(x, y);
		}
	}

	/**
	 * inserts a Papillon at the indicated position, and adds it to the UpTable
	 * 
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 */
	public void inserePapillon(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new Papillon(x, y);
			UpTable.add(tableau[x][y]);
		}
	}

	public void insereAlien(int x, int y) {
		if (x > 0
				&& x < longueur - 1
				&& y > 0
				&& y < hauteur - 1
				&& (tableau[x][y] == null || (tableau[x][y] != perso && tableau[x][y] != sortie))) {
			tableau[x][y] = new Papillon(x, y);
			UpTable.add(tableau[x][y]);
		}
	}

	/**
	 * fonction de mise à jour du niveau, met a jour toutes les Cases
	 * susceptibles d'être mises à jour dans le niveau, ouvre la sortie si les
	 * conditions sont remplies
	 * 
	 * Updates thelevel, updates every case that could be updated in this level
	 * opens up the exit if the conditions are met
	 */
	public void refresh() {
		tmax = tmax - Variables.CYCLES * Variables.FRAME / (float) 1000;
		int i = 0;
		perso.refresh(this);
		trieUpTable();
		while (i < UpTable.size()) {
			UpTable.get(i).refresh(this);
			i++;
		}
		cleanUpTable();
		if (!sortie.isOuverte() && dscore <= 0) {
			sortie.Ouvrir();
		}
	}

	/**
	 * displays the level in text mode, use when bugged
	 */
	public void afficheDebug() {
		int x, y;
		for (y = 0; y < hauteur; y++) {
			for (x = 0; x < longueur; x++) {
				System.out.print(tableau[x][y].ID() + " ");
			}
			System.out.print("\n");
		}
		System.out.print(UpTable.size());
		System.out.println(UpTable.toString());
		System.out.println("Diamant restant :" + dscore);
	}

	public Case getCase(int x, int y) {
		if (x >= 0 && x < longueur && y >= 0 && y < hauteur) {
			return tableau[x][y];
		} else {
			return null;
		}
	}

	/**
	 * recovers the character
	 */
	public Personnage getPerso() {
		return perso;
	}

	/**
	 * recovers the exit
	 */
	public Sortie getSortie() {
		return sortie;
	}

	/**
	 * échange les deux cases aux positions qui sont transmises en paramètres
	 */
	public void echangeCases(int x1, int y1, int x2, int y2) {
		Case temp = tableau[x1][y1];
		tableau[x1][y1] = tableau[x2][y2];
		tableau[x2][y2] = temp;
		tableau[x1][y1].setXY(x1, y1);
		tableau[x2][y2].setXY(x2, y2);
	}

	//
	//
	//
	//
	//
	//
	//
	//

	/**
	 * 
	 * adds the in update table the case in which the position is transmitted
	 * 
	 * @param x
	 * @param y
	 */
	public void addUptable(int x, int y) {
		if (!UpTable.contains(tableau[x][y]) && tableau[x][y] != perso
				&& tableau[x][y] != sortie) {
			UpTable.add(UpTable.size(), tableau[x][y]);
		}
	}

	/**
	 * 
	 *
	 * adds in the update table the cases in a rectangle of 2*3, above the
	 * transmitted position
	 */
	public void remplirUpTable(int x, int y) {
		addUptable(x, y - 1);
		addUptable(x + 1, y - 1);
		addUptable(x - 1, y - 1);
		addUptable(x + 1, y);
		addUptable(x - 1, y);
	}

	/**
	 * sorts the update table, the lower cases have a higher priority than the
	 * higher cases
	 */
	public void trieUpTable() {
		int i = 0;
		int j, pmin;
		Case temp;

		while (i < UpTable.size() - 1) {
			pmin = i;
			j = i + 1;
			while (j < UpTable.size()) {
				if (UpTable.get(j).isSuperior(UpTable.get(pmin))) {
					pmin = j;
				}
				j++;
			}
			temp = UpTable.set(i, UpTable.get(pmin));
			UpTable.set(pmin, temp);
			i++;
		}
	}

	/**
	 * cleans the update table of all elements that cannot move
	 */
	public void cleanUpTable() {
		int i = 0;
		while (i < UpTable.size()) {
			if ((UpTable.get(i).stayInUpTable())
					&& !((Chutable) UpTable.get(i)).instable()) {
				UpTable.remove(i);
			} else {
				i++;
			}
		}
	}

	/**
	 * delete the update table
	 */
	public void remUptable(Case C) {
		UpTable.remove(C);
	}

	/**
	 * recovers the update table
	 */
	public Case[] getUpTable() {
		return (Case[]) UpTable.toArray();
	}

	//
	//
	//
	//
	//
	//
	//

	/**
	 * recovers if the level is finished
	 */
	public boolean isFini() {
		return fini;
	}

	/**
	 * sets the level to a finished state
	 */
	public void setFini() {
		fini = true;
	}

	/**
	 * décrémente le nombre de diamants à trouver
	 */
	public void AddDscore() {
		dscore--;
	}

	/**
	 * 
	 * recovers the level's height
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * defines the level's height
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	/**
	 * recovers the level's length
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * defines the level's length
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	/**
	 * defines the number of diamond's required to finish the level
	 */
	public void setDscore(int dscore) {
		this.dscore = dscore;
	}

	/**
	 * recovers the number of diamonds needed to finish the level returns 0 if
	 * negative
	 */
	public int getDscore() {
		if (dscore < 0) {
			return 0;
		}
		return dscore;
	}

	/**
	 * 
	 * exports the level as a csv file
	 */
	public void exporter(String niveau) {
		Writer writer = null;
		try {
			int x, y;

			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("niveaux/"
							+ niveau + ".csv"), "utf-8"));

			writer.write("LEVEL," + longueur + "," + hauteur + "\n");

			for (y = 0; y < hauteur; y++) {
				for (x = 0; x < longueur; x++) {
					writer.write(getCase(x, y).ID() + ",");
				}
				writer.write("\n");
			}
			writer.write("DIAMOND," + dscore + "\n");
			writer.write("TIME," + (int) tmax + "\n");
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * imports the level as a csv file
	 */
	public void importer(String niveau) {
		String ligne = "";
		String separateur = ",";
		BufferedReader br = null;

		try {
			int x, y;
			br = new BufferedReader(new FileReader(niveau));

			if ((ligne = br.readLine()) != null) {
				String[] propriete = ligne.split(separateur);

				longueur = Integer.parseInt(propriete[1]);
				hauteur = Integer.parseInt(propriete[2]);

				tableau = new Case[longueur][hauteur];

				for (y = 0; y < hauteur; y++) {
					ligne = br.readLine();
					String[] cases = ligne.split(separateur);

					for (x = 0; x < longueur; x++) {
						switch (cases[x]) {
						case "M":
							insereMurIndestructible(x, y);
							break;
						case "N":
							insereMurNormal(x, y);
							break;
						case "P":
							inserePersonage(x, y);
							break;
						case "S":
							insereSortie(x, y);
							break;
						case "B":
							insereBoue(x, y);
							break;
						case "D":
							insereDiamant(x, y);
							break;
						case "R":
							insereRocher(x, y);
							break;
						case "X":
							insereMurMagique(x, y);
							break;
						case "E":
							inserePapillon(x, y);
							break;
						case "A":
							insereAlien(x, y);
							break;
						default:
							insereVide(x, y);
							break;
						}
					}
				}
				try {
					ligne = br.readLine();
					propriete = ligne.split(separateur);
					dscore = Integer.parseInt(propriete[1]);
				} catch (NullPointerException e) {
					dscore = 0;
				}
				try {
					ligne = br.readLine();
					propriete = ligne.split(separateur);
					tmax = Float.parseFloat(propriete[1]);
				} catch (NullPointerException e) {
					tmax = 120;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * met jour la table d'animation ainsi que le type d'animation du personnage
	 */
	public void refreshAnim() {
		perso.refreshAnim();
		TableAnimation.refreshAnim();
	}

	/**
	 * recovers the bonus time of the player, returns 0 if negative
	 */
	public int getTmax() {
		if (tmax < 0) {
			return 0;
		}
		return (int) tmax;
	}

	/**
	 * defines the bonus time
	 */
	public void setTmax(int tmax) {
		this.tmax = tmax;
	}

	/**
	 * ajoute la valeur transmise en paramètre au score
	 */
	public void addToScore(int value) {
		score += value;
	}

	/**
	 * recovers the level's score
	 */
	public int getScore() {
		return score;
	}

}
