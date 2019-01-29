package com.carhub.api

import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.{Calendar, Locale}

import com.carhub.api.gearheads.model.{Gearhead, Gender}
import com.carhub.api.gearheads.model.files.Photo
import com.carhub.api.gearheads.model.locations.{Location, Workplace}
import com.carhub.api.gearheads.repositories._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{ApplicationArguments, ApplicationRunner}
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component


@Component
class InitialDataLoader(@Autowired
                        val channelRepository: ChannelRepository,
                        val choiceRepository: ChoiceRepository,
                        val eventMembershipRepository: EventMembershipRepository,
                        val eventRepository: EventRepository,
                        val fileRepository: FileRepository,
                        val gearheadRepository: GearheadRepository,
                        val locationRepository: LocationRepository,
                        val messageRecipientRepository: MessageRecipientRepository,
                        val messageRepository: MessageRepository,
                        val photoAlbumRepository: PhotoAlbumRepository,
                        val photoRepository: PhotoRepository,
                        val photoTagRepository: PhotoTagRepository,
                        val pollRepository: PollRepository,
                        val postRepository: PostRepository,
                        val relationshipRepository: RelationshipRepository,
                        val threadRepository: ThreadRepository,
                        val topicMembershipRepository: TopicMembershipRepository,
                        val workplaceRepository: WorkplaceRepository)
                        extends ApplicationRunner {

  def run(args: ApplicationArguments): Unit = {

    val gearhead = new Gearhead
    gearheadRepository.save(gearhead)

    val photo: Photo = createPhoto(gearhead)

    gearhead.profilePhoto = photo
    gearhead.coverPhoto = photo
    gearhead.email = "amine.elkouhen@gmail.com"
    gearhead.firstName = "Amine"
    gearhead.firstName = "El Kouhen"
    gearhead.enabled = true
    gearhead.gender = Gender.MALE
    gearhead.username = "aelkouhen"
    gearhead.aboutMe = "lorem ipsum"
    val format = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
    val date = format.parse("15/11/1986")
    gearhead.birthDay = date


    val location: Location = createLocation({
                                            val location = new Location
                                            location.street = "36 Rue Saint Henri"
                                            location.city = "La Madeleine"
                                            location.zipCode = "59110"
                                            location.state = "Nord"
                                            location.country = "France"

                                            location
                                          })

    gearhead.hometownLocation = location
    gearhead.currentLocation = location

    val workplace: Workplace = createWorkplace(format)
    gearhead.workplace = workplace

    gearhead.creationTime = Calendar.getInstance().getTime()
    gearhead.updateTime = Calendar.getInstance().getTime()

    gearheadRepository.save(gearhead)

  }

  private def createWorkplace(format: SimpleDateFormat) = {
    val workplace = new Workplace
    workplace.companyName = "Amaris"
    workplace.position = "Software Engineer"
    workplace.location = createLocation({
                                        val location = new Location
                                        location.street = "15 Place des Bleuets"
                                        location.city = "Lille"
                                        location.zipCode = "59000"
                                        location.state = "Nord"
                                        location.country = "France"

                                        location
                                      })

    workplace.description = "Consultancy company"
    workplace.startDate = format.parse("03/09/2018")
    workplaceRepository.save(workplace)

    workplace
  }

  private def createPhoto(gearhead: Gearhead) = {
    val photo = new Photo
    val picture = new ClassPathResource("image/myPic.jpg")
    val inputStream = picture.getInputStream
    val arrayPic = Stream.continually(inputStream.read).takeWhile(-1 !=).map(_.toByte).toArray
    inputStream.close()
    photo.size = picture.contentLength()
    photo.caption = "Amine's Pic"
    photo.created = Calendar.getInstance().getTime()
    photo.owner = gearhead
    photo.content = arrayPic
    photoRepository.saveAndFlush(photo)

    photo
  }

  private def createLocation (location: Location) = {
    locationRepository.save(location)
    location
  }
}
