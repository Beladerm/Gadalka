import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OptionsViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    fun setName(name: String) {
        _name.value = name
    }
}
