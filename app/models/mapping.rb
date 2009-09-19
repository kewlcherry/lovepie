class Mapping < ActiveRecord::Base
    belongs_to :cause
    belongs_to :organisation
    belongs_to :user
    
    validates_uniqueness_of :cause_id, :scope => [:organisation_id, :user_id]
end
