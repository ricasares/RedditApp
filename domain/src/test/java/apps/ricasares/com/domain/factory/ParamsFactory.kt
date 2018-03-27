package apps.ricasares.com.domain.factory

import apps.ricasares.com.domain.factory.DataFactory
import apps.ricasares.com.domain.interactor.browse.GetListingUseCase

/**
 * Created by ricardo casarez on 3/22/18.
 */
class ParamsFactory {
    companion object {
        fun getListingsParams() : GetListingUseCase.Params {
            return GetListingUseCase.Params(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomInt())
        }
    }
}