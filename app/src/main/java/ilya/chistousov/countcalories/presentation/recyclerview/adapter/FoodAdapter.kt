package ilya.chistousov.countcalories.presentation.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ilya.chistousov.countcalories.R
import ilya.chistousov.countcalories.domain.model.Food

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private val foods = mutableListOf<Food>()

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val foodName = view.findViewById<TextView>(R.id.textViewFoodName)
        private val foodCalories = view.findViewById<TextView>(R.id.textViewCalories)
        private val foodProteins = view.findViewById<TextView>(R.id.textViewProteins)
        private val foodFats = view.findViewById<TextView>(R.id.textViewFats)
        private val foodCarbs = view.findViewById<TextView>(R.id.textViewCarbs)

        fun onBind(food: Food) {
            foodName.text = food.name
            foodCalories.text = food.calories.toString()
            foodProteins.text = food.proteins.toString()
            foodFats.text = food.fats.toString()
            foodCarbs.text = food.carbs.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
       holder.onBind(foods[position])
    }

    override fun getItemCount() = foods.size

    fun setList(foods: List<Food>) {
        this.foods.clear()
        this.foods.addAll(foods)
    }
}