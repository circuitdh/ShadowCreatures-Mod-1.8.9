package upcraftlp.shadowcreatures.entity.models;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class BasicDemon extends EntityMob{

	public BasicDemon(World worldIn) {
		super(worldIn);
		 this.setAITasks();
	     this.setSprinting(false);
	}

	private void setAITasks() {}

}
