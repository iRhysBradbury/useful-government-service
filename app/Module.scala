import com.google.inject.AbstractModule
import modules.SystemBootstrap

class Module extends AbstractModule {

  override def configure() = {
    //setup db
    bind(classOf[SystemBootstrap]).asEagerSingleton

  }
}