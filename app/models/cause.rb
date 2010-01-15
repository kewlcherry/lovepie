class Cause < ActiveRecord::Base

  MAX_CAUSES_PER_ORGANISATION = 3

  belongs_to :organisation
  belongs_to :user
  validates_presence_of :name, :organisation_id, :user_id
  validates_uniqueness_of :name, :scope => [:organisation_id, :user_id]

  validate :validate_max

  def validate_max
    errors.add_to_base("You cannot have more than #{MAX_CAUSES_PER_ORGANISATION} causes per organisation.") unless
             Cause.count(:conditions => ["user_id = ? AND organisation_id = ?" ,user, organisation]) < MAX_CAUSES_PER_ORGANISATION
  end
end
