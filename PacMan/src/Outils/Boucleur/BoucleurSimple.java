package Outils.Boucleur;

import Launch.Step;
import Class.Fantome;
import Class.Entite;

public class BoucleurSimple extends Boucleur{
    @Override
    public void run() {
        while(actif){
            beep();
            try{
                if(Step.getManager().getPacmanEncour().isBuff() && Step.getManager().getPacmanEncour().getCount()<= 50){
                    if(Step.getManager().getPacmanEncour().getCount()<50){
                        Step.getManager().getPacmanEncour().ajoutCount();
                                           }
                    else{
                        Step.getManager().getPacmanEncour().resetCount();
                        Step.getManager().getPacmanEncour().setBuff(false);
                        for(Entite e : Step.getManager().getLesEntites()){
                            if(e.getClass() == Fantome.class){
                                ((Fantome) e).setMangeable(false);
                            }
                        }
                    }
                }
                Thread.sleep(250);
            } catch (InterruptedException e) {
                actif = false;
            }

        }
    }
}
