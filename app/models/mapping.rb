class Mapping < ActiveRecord::Base
    belongs_to :cause
    belongs_to :organisation
    belongs_to :user
end
